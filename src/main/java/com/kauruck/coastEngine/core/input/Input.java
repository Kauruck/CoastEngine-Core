package com.kauruck.coastEngine.core.input;

import com.kauruck.coastEngine.core.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Input {

    //Keyboard
    private static final boolean[] keyActive = new boolean[KeyCode.values().length];
    private static final boolean[] keyDown = new boolean[KeyCode.values().length];
    private static final boolean[] keyUp = new boolean[KeyCode.values().length];
    //Mouse
    private static final boolean[] mouseActive = new boolean[3];
    private static final boolean[] mouseDown = new boolean[3];
    private static final boolean[] mouseUp = new boolean[3];
    private static float scrollWheelPos = 0;
    private static Vector2 mousePos = new Vector2();
    private static boolean mouseMoved = false;

    //Listeners
    private static final List<KeyListener> keyListeners = new ArrayList<>();
    private static final List<MouseListener> mouseListeners = new ArrayList<>();



    //Keyboard inputs
    /**
     * Checks weather the key is down
     * @param keyCode The key
     * @return False for up, and true for down
     */
    public static boolean getKey(@org.jetbrains.annotations.NotNull KeyCode keyCode){
        return keyActive[keyCode.indexOf()];
    }

    /**
     * Checks weather a key was just pressed down
     * @param keyCode The key
     * @return False for not being just pressed, true for being
     */
    public static boolean getKeyDown(KeyCode keyCode){
        return keyDown[keyCode.indexOf()];
    }

    /**
     * Checks weather a key was just released
     * @param keyCode The key
     * @return False for not being just released, true for being
     */
    public static boolean getKeyUp(KeyCode keyCode){
        return keyUp[keyCode.indexOf()];
    }

    /**
     * Register that a key was just pressed
     * Converts the AltGr key form crt + alt
     * @param keyCode The key
     */
    public static void onKeyDown(KeyCode keyCode){
        if(Input.getKey(KeyCode.Crt) && keyCode == KeyCode.Alt)
            keyCode = KeyCode.AltGr;
        keyActive[keyCode.indexOf()] = true;
        keyDown[keyCode.indexOf()] = true;
    }

    /**
     * Register that a key was just released<br>
     * Converts the AltGr key from crt + alt
     * @param keyCode The key
     */
    public static void onKeyUp(KeyCode keyCode){
        if(Input.getKey(KeyCode.AltGr) && keyCode == KeyCode.Crt)
            keyCode = KeyCode.AltGr;
        if(Input.getKeyUp(KeyCode.AltGr) && keyCode == KeyCode.Alt)
            keyCode = KeyCode.None;
        keyActive[keyCode.indexOf()] = false;
        keyUp[keyCode.indexOf()] = true;
    }

    //Mouse inputs
    /**
     * Checks weather the mouse button is down
     * @param button The button
     * @return False for up, and true for down
     */
    public static boolean getMouseButton(MouseButton button){
        return mouseActive[button.getNumber()];
    }

    /**
     * Checks weather a mouse button was just pressed down
     * @param button The button
     * @return False for not being just pressed, true for being
     */
    public static boolean getMouseButtonDown(MouseButton button){
        return mouseDown[button.getNumber()];
    }

    /**
     * Checks weather a mouse button was just released
     * @param button The button
     * @return False for not being just released, true for being
     */
    public static boolean getMouseButtonUp(MouseButton button){
        return mouseUp[button.getNumber()];
    }

    /**
     * Gets the position of the scroll wheel
     * @return The position
     */
    public static float getScrollWheelPos(){
        return scrollWheelPos;
    }

    /**
     * Returns the position of the mouse
     * @return The position
     */
    public static Vector2 getMousePos(){
        return mousePos;
    }

    /**
     * Checks weather the mouse moved
     * @return False if the mouse did not move, true if it did
     */
    public static boolean mouseMoved(){
        return mouseMoved;
    }

    /**
     * Register that a mouse button was just pressed
     * @param button The button
     */
    public static void onMouseButtonDown(MouseButton button){
        mouseActive[button.getNumber()] = true;
        mouseDown[button.getNumber()] = true;
    }

    /**
     * Register that a mouse button was just released
     * @param button The button
     */
    public static void onMouseButtonUp(MouseButton button){
        mouseActive[button.getNumber()] = false;
        mouseUp[button.getNumber()] = true;
    }

    /**
     * Updates the position of the scroll wheel
     * @param amount The amount scrolled by
     */
    public static void onScroll(float amount){
        scrollWheelPos += amount;
    }

    /**
     * Updates the position of the mouse
     * @param offset The amount the mouse moved by
     */
    public static void onMouseMove(Vector2 offset){
        mousePos = offset;
        mouseMoved = true;
    }



    //General
    /**
     * Updates the inputs<br>
     *
     * Imported: This should be called after you dealt with the up and down event
     */
    public static void update(){
        //Process Listeners
        keyListeners.stream()
                .filter((listener) -> (getKey(listener.getKeyCode()) && listener.getAction() == InputAction.Repeat) ||
                        (getKeyDown(listener.getKeyCode()) && listener.getAction() == InputAction.Down) ||
                        (getKeyUp(listener.getKeyCode()) && listener.getAction() == InputAction.Up))
                .forEach(KeyListener::trigger);

        mouseListeners.stream()
                .filter((listener) -> (getMouseButton(listener.getMouseCode()) && listener.getAction() == InputAction.Repeat) ||
                        (getMouseButtonDown(listener.getMouseCode()) && listener.getAction() == InputAction.Down) ||
                        (getMouseButtonUp(listener.getMouseCode()) && listener.getAction() == InputAction.Up))
                .forEach(MouseListener::trigger);
        //Keyboard reset
        Arrays.fill(keyDown, false);
        Arrays.fill(keyUp, false);
        //Mouse reset
        Arrays.fill(mouseDown, false);
        Arrays.fill(mouseUp, false);
        mouseMoved = false;
    }

    public static void registerKeyListener(KeyCode keyCode, InputAction action, IOnEvent onEvent){
        keyListeners.add(new KeyListener(keyCode, onEvent, action));
    }

    public static void registerMouseListener(MouseButton mouseButton, InputAction action, IOnEvent onEvent){
        mouseListeners.add(new MouseListener(mouseButton, onEvent, action));
    }

    private static class KeyListener {
        private final KeyCode keyCode;
        private final IOnEvent event;
        private final InputAction action;

        public KeyListener(KeyCode keyCode, IOnEvent event, InputAction action) {
            this.keyCode = keyCode;
            this.event = event;
            this.action = action;
        }

        public void trigger(){
            event.execute();
        }

        public KeyCode getKeyCode() {
            return keyCode;
        }

        public InputAction getAction() {
            return action;
        }
    }

    private static class MouseListener {
        private final MouseButton mouseCode;
        private final IOnEvent event;
        private final InputAction action;

        public MouseListener(MouseButton keyCode, IOnEvent event, InputAction action) {
            this.mouseCode = keyCode;
            this.event = event;
            this.action = action;
        }

        public void trigger(){
            event.execute();
        }

        public MouseButton getMouseCode() {
            return mouseCode;
        }

        public InputAction getAction() {
            return action;
        }
    }
}
