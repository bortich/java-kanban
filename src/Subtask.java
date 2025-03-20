public class Subtask extends Task {
    private final int epicId;

    public Subtask(Integer id, String name, String description, int epicId) {
        super(id, name, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}