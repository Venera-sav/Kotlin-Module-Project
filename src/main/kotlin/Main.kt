import kotlin.system.exitProcess

fun main() {
    println("Привет !")
    // Создаем несколько архивов и заметок для тестирования
    val archives = mutableListOf(Archive("Первый архив").apply {
        notes.addAll(listOf(
            Note("Первая заметка", "Текст первой заметки."),
            Note("Вторая заметка", "Текст второй заметки.")
        ))
    },
        Archive("Второй архив").apply {
            notes.addAll(listOf(
                Note("Третья заметка", "Текст третьей заметки."),
                Note("Четвертая заметка", "Текст четвертой заметки.")
            ))
        })
// Главное меню
    val mainMenu = Menu("Выберите действие:", listOf(
        "Добавить архив",
        "Просмотреть архивы",
        "Выход"
    ))

    while (true) {
        mainMenu.print()
        when (mainMenu.getUserChoice()) {
            0 -> {
                // Создание нового архива
                print("Введите название нового архива: ")
                val archiveName = readLine() ?: ""
                archives.add(Archive(archiveName))
                println("Архив '$archiveName' успешно создан.")
            }
            1 -> {
                // Просмотр списка архивов
                if (archives.isEmpty()) {
                    println("Нет доступных архивов.")
                } else {
                    val archiveMenu = Menu("Выберите архив:", archives.map { it.name } + "Выход")
                    archiveMenu.print()
                    val archiveChoice = archiveMenu.getUserChoice()
                    if (archiveChoice == archives.size) {
                        // Выход в главное меню
                        continue
                    }
                    val archive = archives[archiveChoice]
                    val noteMenu = Menu("Выберите заметку:", archive.notes.map { it.title } + listOf("Добавить заметку", "Выход"))
                    while (true) {
                        noteMenu.print()
                        val noteChoice = noteMenu.getUserChoice()
                        if (noteChoice == archive.notes.size) {
                            // Добавление новой заметки
                            print("Введите название новой заметки: ")
                            val noteTitle = readLine() ?: ""
                            print("Введите текст новой заметки: ")
                            val noteText = readLine() ?: ""
                            archive.notes.add(Note(noteTitle, noteText))
                            println("Заметка '$noteTitle' успешно добавлена.")
                            // Обновление списка заметок в меню выбора
                            noteMenu.options = archive.notes.map { it.title } + listOf("Добавить заметку", "Выход")
                        } else if (noteChoice == archive.notes.size + 1) {
                            // Выход в список архивов
                            break
                        } else {
                            // Просмотр заметки
                            val note = archive.notes[noteChoice]
                            val viewNoteMenu =
                                Menu(note.title, listOf("Просмотреть заметку", "Назад"))
                            while (true) {
                                viewNoteMenu.print()
                                val viewNoteChoice = viewNoteMenu.getUserChoice()
                                if (viewNoteChoice == 0) {
                                    // Вывод текста заметки
                                    println("Заметка: ${note.text}")
                                } else {
                                    // Выход в список заметок
                                    break
                                }
                            }
                        }
                    }
                }
            }
            2 -> {
                // Выход из программы
                println("Выход из программы...")
                exitProcess(0)
            }
            else -> {
                println("Некорректный ввод. Пожалуйста, выберите действие из списка.")
            }
        }
    }
}