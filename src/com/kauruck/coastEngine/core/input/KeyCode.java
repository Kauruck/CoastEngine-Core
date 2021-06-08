package com.kauruck.coastEngine.core.input;

//Key codes for java https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html[14.01.2021]
//https://stackoverflow.com/questions/36344189/how-to-click-windows-key-in-java[14.01.2021]
//https://stackoverflow.com/questions/8795298/java-does-not-produce-proper-altgr-key-event[14.01.2021]

import java.awt.event.KeyEvent;

public enum KeyCode {
    None,
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
    N,
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
    Num5,
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
    Dived,
    Multiply,
    Subtract;

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

    /**
     * Returns the KeyCode for the KeyEvent e<br>
     *
     * <bold>Imported this does not supported AltGr on Windows. Instead Crt and Alt are triggered. If you use Input.onKeyPressedEvent or Input.onKeyReleasedEvent this will be translated.</bold>
     * @param e The KeyEvent
     * @return The KeyCode or none if not supported
     */
    public static KeyCode fromEvent(KeyEvent e){
        return switch (e.getKeyCode()){
            case KeyEvent.VK_A -> A;
            case KeyEvent.VK_B -> B;
            case KeyEvent.VK_C -> C;
            case KeyEvent.VK_D -> D;
            case KeyEvent.VK_E -> E;
            case KeyEvent.VK_F -> F;
            case KeyEvent.VK_G -> G;
            case KeyEvent.VK_H -> H;
            case KeyEvent.VK_I -> I;
            case KeyEvent.VK_J -> J;
            case KeyEvent.VK_K -> K;
            case KeyEvent.VK_L -> L;
            case KeyEvent.VK_M -> M;
            case KeyEvent.VK_N -> N;
            case KeyEvent.VK_O -> O;
            case KeyEvent.VK_P -> P;
            case KeyEvent.VK_Q -> Q;
            case KeyEvent.VK_R -> R;
            case KeyEvent.VK_S -> S;
            case KeyEvent.VK_T -> T;
            case KeyEvent.VK_U -> U;
            case KeyEvent.VK_V -> V;
            case KeyEvent.VK_W -> W;
            case KeyEvent.VK_X -> X;
            case KeyEvent.VK_Y -> Y;
            case KeyEvent.VK_Z -> Z;
            case KeyEvent.VK_1 -> Key1;
            case KeyEvent.VK_2 -> Key2;
            case KeyEvent.VK_3 -> Key3;
            case KeyEvent.VK_4 -> Key4;
            case KeyEvent.VK_5 -> Key5;
            case KeyEvent.VK_6 -> Key6;
            case KeyEvent.VK_7 -> Key7;
            case KeyEvent.VK_8 -> Key8;
            case KeyEvent.VK_9 -> Key9;
            case KeyEvent.VK_0 -> Key0;
            case KeyEvent.VK_CONTROL -> Crt;
            case KeyEvent.VK_SHIFT -> Shift;
            case KeyEvent.VK_TAB -> Tab;
            case KeyEvent.VK_CAPS_LOCK -> CapsLock;
            case KeyEvent.VK_WINDOWS -> Home;
            case KeyEvent.VK_ASTERISK -> KeyAdd;
            case KeyEvent.VK_ALT -> Alt;
            case KeyEvent.VK_ALT_GRAPH -> AltGr;
            case KeyEvent.VK_ESCAPE -> Esc;
            case KeyEvent.VK_BACK_SPACE -> Backspace;
            case KeyEvent.VK_F1 -> F1;
            case KeyEvent.VK_F2 -> F2;
            case KeyEvent.VK_F3-> F3;
            case KeyEvent.VK_F4 -> F4;
            case KeyEvent.VK_F5 -> F5;
            case KeyEvent.VK_F6 -> F6;
            case KeyEvent.VK_F7 -> F7;
            case KeyEvent.VK_F8 -> F8;
            case KeyEvent.VK_F9 -> F9;
            case KeyEvent.VK_F10 -> F10;
            case KeyEvent.VK_F11 -> F11;
            case KeyEvent.VK_F12 -> F12;
            case KeyEvent.VK_NUMPAD1 -> Num1;
            case KeyEvent.VK_NUMPAD2 -> Num2;
            case KeyEvent.VK_NUMPAD3 -> Num3;
            case KeyEvent.VK_NUMPAD4 -> Num4;
            case KeyEvent.VK_NUMPAD5 -> Num5;
            case KeyEvent.VK_NUMPAD6 -> Num6;
            case KeyEvent.VK_NUMPAD7 -> Num7;
            case KeyEvent.VK_NUMPAD8 -> Num8;
            case KeyEvent.VK_NUMPAD9 -> Num9;
            case KeyEvent.VK_NUMPAD0 -> Num0;
            case KeyEvent.VK_ENTER -> Enter;
            case KeyEvent.VK_DELETE -> Del;
            case KeyEvent.VK_INSERT -> Ins;
            case KeyEvent.VK_HOME -> Pos1;
            case KeyEvent.VK_END -> End;
            case KeyEvent.VK_UP -> ArrowUp;
            case KeyEvent.VK_DOWN -> ArrowDown;
            case KeyEvent.VK_LEFT -> ArrowLeft;
            case KeyEvent.VK_RIGHT -> ArrowRight;
            case KeyEvent.VK_NUMBER_SIGN -> Hash;
            case KeyEvent.VK_ADD -> KeyAdd;
            case KeyEvent.VK_PERIOD -> Dot;
            case KeyEvent.VK_COMMA -> Comma;
            case KeyEvent.VK_MINUS -> Dash;
            case KeyEvent.VK_DIVIDE -> Dived;
            case KeyEvent.VK_MULTIPLY -> Multiply;
            case KeyEvent.VK_SUBTRACT -> Subtract;
            default -> None;
        };
    }
}
