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
            val choice = input.toInt()
            if (choice in 0 until options.size) {
                choice
            } else {
                println("Некорректный ввод. Пожалуйста, введите число от 0 до ${options.size - 1}.")
                getUserChoice()
            }
        } else {
            println("Некорректный ввод. Пожалуйста, введите число.")
            getUserChoice()
        }
    }
}
