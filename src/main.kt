import fachada.Fachada
import java.util.*

fun main() {
    
    val input   = Scanner(System.`in`)

    print("Digite uma palavra: ")
    var palavra = input.nextLine().uppercase()

    print("Digite uma dica: ")
    var dica    = input.nextLine().uppercase()

    val control = Fachada(palavra, dica)

    if (!control.validar()) {
        print("Informe uma palavra e uma dica")
    }
    else {
        control.iniciar()

        print("Digite uma letra: ")
        var tentativa = input.nextLine().uppercase()

        while (true) {
            control.jogar(tentativa)

            if (!control.terminou()) {
                print("Digite outra letra: ")
                tentativa = input.nextLine().uppercase()
            }
            else {
                break
            }

        }

        if (control.perdeu()) {
            println("PERDEU :-(")
            println("A palavra era: ${palavra.uppercase()}")
        }
        else {
            println("GANHOU :-)")
            println("A palavra era: ${palavra.uppercase()}")
        }

    }
}
