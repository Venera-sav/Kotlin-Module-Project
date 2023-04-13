class NoteArchive(val name: String = "") {
    private val notes = mutableListOf<Note>()

    fun addNote() {
        print("Введите текст заметки: ")
        val text = readLine() ?: ""
        val title = readLine() ?: ""
        notes.add(Note(title, text))
    }

    fun editNote() {
        val note = selectNote() ?: return
        print("Введите новый текст заметки: ")
        val text = readLine() ?: ""
        note.text = text
    }

    fun deleteNote() {
        val note = selectNote() ?: return
        notes.remove(note)
    }

    fun printNotes() {
        if (notes.isEmpty()) {
            println("Заметок нет")
        } else {
            notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.text}")
            }
        }
    }

    private fun selectNote(): Note? {
        if (notes.isEmpty()) {
            println("Заметок нет")
            return null
        }
        val options = mutableListOf<String>()
        for (i in notes.indices) {
            options.add("${i + 1}. ${notes[i].text}")
        }
        options.add("${options.size + 1}. Назад")
        while (true) {
            println("\nСписок заметок:")
            options.forEach { println(it) }
            print("Выберите заметку: ")
            val input = readLine()?.toIntOrNull()
            if (input == null) {
                println("Ошибка: введите число")
            } else if (input !in 1..options.size) {
                println("Ошибка: выбранной заметки не существует")
            } else if (input == options.size) {
                return null
            } else {
                return notes[input - 1]
            }
        }
    }
}