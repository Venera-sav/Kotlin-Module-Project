// Класс для работы с заметками
class Note(var title: String, var text: String) {
    override fun toString(): String {
        return title
    }
}

class Archive(var name: String) {
    val notes = mutableListOf<Note>()
}