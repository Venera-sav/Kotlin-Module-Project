// Класс для работы с меню
class Menu(var title: String, var options: List<String>) {
    fun print() {
        println(title)
        options.forEachIndexed { index, option -> println("$index. $option") }
    }

    fun getUserChoice(): Int {
        print("Введите число: ")
        val input = readLine()
        return if (input != null && input.matches(Regex("^\\d+$"))) {
            input.toInt()
        } else {
            println("Некорректный ввод. Пожалуйста, введите число.")
            getUserChoice()
        }
    }
}