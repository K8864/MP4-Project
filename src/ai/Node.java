package ai;

public class Node {
    Node parent;
    public int col;
    public int row;
    public int gCost;
    public int hCost;
    public int fCost;
    public boolean solid;
    public boolean open;
    public boolean checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public int getgCost() {
        return gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public int getfCost() {
        return fCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
