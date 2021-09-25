/**
 * @author AJWuu
 */

package mementoDesignPattern;

public class EditorData {
    private String text;
    private Color color;

    public EditorData(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Snapshot createSnapshot() {
        return new Snapshot(text, color);
    }

    public void restoreFromSnapshot(Snapshot snapshot) {
        this.text = snapshot.text;
        this.color = snapshot.color;
    }

    public void print() {
        System.out.println("Text: " + text);
        System.out.println("Color:" + color);
    }

    public static class Snapshot {
        private String text;
        private Color color;

        public Snapshot(String text, Color color) {
            this.text = text;
            this.color = color;
        }
    }
}
