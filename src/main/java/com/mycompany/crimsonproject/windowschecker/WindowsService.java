package com.mycompany.crimsonproject.windowschecker;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;
import org.javatuples.Triplet;

/**
 *
 * @author Devmachine
 */
public class WindowsService {

    private String windowTitle; // Janela ativa
    private String exeName = ""; // Nome do executável
    private boolean isFullscreen; // Fullscreen?
    private HWND foregroundWindow;

    public WindowsService() {
        foregroundWindow = User32.INSTANCE.GetForegroundWindow(); // Obtém a janela que está atualmente em primeiro plano
    }

    /**
     * Checks the currently active window and returns information about it,
     * including: - The window's title, - The executable name associated with
     * the active window, - Whether the window is in fullscreen mode.
     *
     * @return a {@link Triplet} containing: - The window's title as a
     * {@link String}, - The executable name associated with the window as a
     * {@link String}, - A {@link Boolean} indicating whether the window is in
     * fullscreen (true) or not (false), or {@code null} if no active window is
     * found.
     */
    public Triplet<String, String, Boolean> activeWindowsChecker() {

        if (foregroundWindow != null) { // Verifica se a janela ativa foi identificada (não é nula)

            windowTitle = currentWindowsTitle(); // Busca o título da janela em primeiro plano
            exeName = currentExecutable(); // Obtém o nome do executável em primeiro plano
            isFullscreen = isCurrentWindowFullscreen(); // Verifica se em primeiro plano está em full screen

            return new Triplet<>(windowTitle, exeName, isFullscreen); // Retorna um Triplet com título da janela, nome do executável e se está em fullscreen
        }

        return null; // Retorna null caso não haja uma janela ativa
    }

    /**
     * Returns the title of the currently active window in Windows.
     *
     * Uses the User32 library to get the foreground window title. If no window
     * is in the foreground, returns an empty string.
     *
     * @return The title of the active window, or an empty string if none.
     */
    public String currentWindowsTitle() {

        if (foregroundWindow != null) {
            char[] windowText = new char[512]; // Cria um buffer para armazenar o título da janela
            User32.INSTANCE.GetWindowText(foregroundWindow, windowText, 512); // Obtém o título da janela ativa usando o handle
            return Native.toString(windowText);// Converte o título da janela para uma String
        }
        return "";
    }

    /**
     * Checks if the currently active window is in fullscreen mode.
     *
     * Retrieves the window's position and compares it with the screen
     * dimensions to determine if the window covers the entire screen.
     *
     * @return true if the window is fullscreen, false otherwise.
     */
    public boolean isCurrentWindowFullscreen() {

        if (foregroundWindow != null) {
            RECT windowRect = new RECT(); // Cria um objeto RECT para armazenar as coordenadas da janela
            User32.INSTANCE.GetWindowRect(foregroundWindow, windowRect); // Obtém as dimensões (posição) da janela ativa

            int screenWidth = User32.INSTANCE.GetSystemMetrics(User32.SM_CXSCREEN); // Obtém a largura da tela
            int screenHeight = User32.INSTANCE.GetSystemMetrics(User32.SM_CYSCREEN); // Obtém a altura da tela

            return (windowRect.left == 0 && windowRect.top == 0
                    && windowRect.right == screenWidth && windowRect.bottom == screenHeight); // Verifica se a janela ocupa toda a tela
        }
        return false;
    }

    /**
     * Retrieves the name of the executable associated with the currently active
     * window.
     *
     * Obtains the process ID of the active window, then queries the process for
     * its executable name.
     *
     * @return The name of the executable, or an empty string if it cannot be
     * determined.
     */
    public String currentExecutable() {
        String executableName = "";

        if (foregroundWindow != null) {
            IntByReference processId = new IntByReference(); // Cria uma referência para armazenar o ID do processo associado à janela
            User32.INSTANCE.GetWindowThreadProcessId(foregroundWindow, processId); // Obtém o ID do processo que criou a janela ativa

            HANDLE process = Kernel32.INSTANCE.OpenProcess(
                    WinNT.PROCESS_QUERY_INFORMATION | WinNT.PROCESS_VM_READ, // Abre o processo para consulta de informações
                    false, // Não herda identificadores
                    processId.getValue()); // ID do processo

            if (process != null) { // Se o processo for aberto com sucesso
                char[] exePath = new char[512]; // Cria um buffer para armazenar o caminho do executável
                Psapi.INSTANCE.GetModuleBaseNameW(process, null, exePath, 512); // Obtém o nome do executável associado ao processo
                executableName = Native.toString(exePath); // Converte o caminho do executável para uma String
                Kernel32.INSTANCE.CloseHandle(process); // Fecha o handle do processo
            }
        }
        return executableName;
    }

    /**
     * Interface for accessing user32.dll functions for interacting with Windows
     * GUI.
     */
    public interface User32 extends W32APIOptions {

        User32 INSTANCE = Native.load("user32", User32.class, DEFAULT_OPTIONS);

        /**
         * Retrieves the handle of the currently active window.
         *
         * @return the handle of the active window.
         */
        HWND GetForegroundWindow();

        /**
         * Retrieves the title of a window.
         *
         * @param hWnd the handle of the window.
         * @param lpString the character array to store the window title.
         * @param nMaxCount the maximum number of characters to be written to
         * lpString.
         * @return the number of characters copied to lpString.
         */
        int GetWindowText(HWND hWnd, char[] lpString, int nMaxCount);

        /**
         * Retrieves the process ID of the window's thread.
         *
         * @param hWnd the handle of the window.
         * @param lpdwProcessId the reference to store the process ID.
         * @return the thread identifier.
         */
        int GetWindowThreadProcessId(HWND hWnd, IntByReference lpdwProcessId);

        /**
         * Retrieves the dimensions of the window.
         *
         * @param hWnd the handle of the window.
         * @param rect the RECT structure to store the window dimensions.
         * @return {@code true} if successful, {@code false} otherwise.
         */
        boolean GetWindowRect(HWND hWnd, RECT rect);

        /**
         * Retrieves information about the system, such as screen width and
         * height.
         *
         * @param nIndex the index for the system information to retrieve.
         * @return the requested system information.
         */
        int GetSystemMetrics(int nIndex);

        int SM_CXSCREEN = 0; // Screen width
        int SM_CYSCREEN = 1; // Screen height
    }

    /**
     * Interface for accessing psapi.dll functions for interacting with process
     * information in Windows.
     */
    public interface Psapi extends W32APIOptions {

        Psapi INSTANCE = Native.load("psapi", Psapi.class, DEFAULT_OPTIONS); // Loads the psapi.dll library and maps the Psapi interface with default options

        /**
         * Retrieves the base name of a module (executable) for a given process.
         *
         * @param hProcess the handle of the process.
         * @param hModule the handle of the module.
         * @param lpBaseName the character array to store the base name of the
         * module.
         * @param nSize the size of the buffer to store the base name.
         * @return the number of characters written to lpBaseName.
         */
        int GetModuleBaseNameW(HANDLE hProcess, HMODULE hModule, char[] lpBaseName, int nSize); // Retrieves the base module name of a process
    }

    /**
     * Interface for accessing kernel32.dll functions for interacting with
     * processes and handles in Windows.
     */
    public interface Kernel32 extends W32APIOptions {

        Kernel32 INSTANCE = Native.load("kernel32", Kernel32.class, DEFAULT_OPTIONS); // Loads the kernel32.dll library and maps the Kernel32 interface with default options

        /**
         * Opens a process with the specified process ID and desired access
         * level.
         *
         * @param dwDesiredAccess the desired access level for the process.
         * @param bInheritHandle indicates whether the handle should be
         * inherited.
         * @param dwProcessId the ID of the process to open.
         * @return a handle to the opened process.
         */
        HANDLE OpenProcess(int dwDesiredAccess, boolean bInheritHandle, int dwProcessId); // Opens a process with the specified process ID and desired access

        /**
         * Closes a handle to a process or other object.
         *
         * @param hObject the handle to be closed.
         * @return {@code true} if the handle was successfully closed,
         * {@code false} otherwise.
         */
        boolean CloseHandle(HANDLE hObject); // Closes a process handle
    }

}
