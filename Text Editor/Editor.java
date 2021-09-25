package mementoDesignPattern;

/**
 * @author AJWuu
 */

import java.util.Stack;

public class Editor {
    private EditorData editorData;
    private Stack<EditorData.Snapshot> snapshots; //To create and store current state

    public Editor(EditorData editorData) {
        this.editorData = editorData;
        this.snapshots = new Stack<>();
    }

    public void changeText(String newText) {
        snapshots.push(editorData.createSnapshot());
        editorData.setText(newText);
    }

    public void changeColor(Color newColor) {
        snapshots.push(editorData.createSnapshot());
        editorData.setColor(newColor);
    }

    public void undo() {
        if (snapshots.isEmpty()) {
            System.out.println("Already back to original state");
        }
        editorData.restoreFromSnapshot(snapshots.pop());
    }

    public void printEditorContent() {
       editorData.print();
    }
}
