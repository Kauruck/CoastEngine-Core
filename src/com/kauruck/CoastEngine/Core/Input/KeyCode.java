package com.kauruck.CoastEngine.Core.Input;

public enum KeyCode {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    I,
    J,
    K,
    L,
    M,
    O,
    P,
    Q,
    R,
    S,
    T,
    U,
    V,
    W,
    X,
    Y,
    Z,
    Key1,
    Key2,
    Key3,
    Key4,
    Key5,
    Key6,
    Key7,
    Key8,
    Key9,
    Key0,
    Crt,
    Shift,
    Tab,
    CapsLock,
    Home,
    Alt,
    AltGr,
    Esc,
    Backspace,
    F1,
    F2,
    F3,
    F4,
    F5,
    F6,
    F7,
    F8,
    F9,
    F10,
    F11,
    F12,
    Num1,
    Num2,
    Num3,
    Num4,
    Num6,
    Num7,
    Num8,
    Num9,
    Num0,
    Enter,
    Del,
    Ins,
    Pos1,
    End,
    ArrowUp,
    ArrowDown,
    ArrowLeft,
    ArrowRight,
    Hash,
    KeyAdd,
    Dot,
    Comma,
    Dash,
    NumAdd,
    NumDived,
    NumMultiply,
    NumSubtract;

    /**
     * Gets the index of the KeyCode in KeyCode.values();
     * @return The index
     */
    public int indexOf(){
        int i = 0;
        for(KeyCode c : KeyCode.values()){
            if(c == this){
                return i;
            }
            i++;
        }

        //This cannot happen;
        return -1;
    }
}
