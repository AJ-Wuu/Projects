/**
 * @author AJWuu
 */

package mementoDesignPattern;

public class Main {
    public static void main(String[] args) {
        EditorData editorData = new EditorData("This is a test case", Color.BLACK);
        Editor editor = new Editor(editorData);
        editor.changeText("This is a test case for Memento pattern");
        editor.changeColor(Color.BLUE);
        editor.changeColor(Color.RED);
        editor.printEditorContent();
        editor.undo();
        editor.printEditorContent();
        editor.undo();
        editor.printEditorContent();
        editor.undo();
        editor.printEditorContent();
    }
}
