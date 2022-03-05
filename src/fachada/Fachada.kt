package fachada

import model.Forca

class Fachada(private var palavra: String, private var dica: String) {

    private lateinit var jogoDaForca: Forca

    // CORES
    private val ANSI_RESET  = "\u001B[38m"
    private val ANSI_GREEN  = "\u001B[32m"
    private val ANSI_RED    = "\u001B[31m"
    private val ANSI_YELLOW = "\u001B[33m"

    fun validar(): Boolean {
        if (this.palavra.isNotEmpty() && this.dica.isNotEmpty()) {
            jogoDaForca = Forca(this.palavra, this.dica)
            return true
        }
        else {
            return false
        }
    }

    fun regra() {
        println("\nBEM-VINDO AO JOGO DA FORCA")
        println("Jogo da forca convencional, porém você pode tentar descobrir a palavra")
        println("Letra a letra com uma quantidade determinada de tentativas\n")
    }

    fun iniciar() {
        jogoDaForca.iniciarJogo()
        this.status()
    }

    fun jogar(palavra: String) {
        try {
            jogoDaForca.arriscarLetra(palavra)

            // MOSTRAR STATUS
            this.status()
        }
        catch (e: Throwable) {
            println(e.message)
        }

    }

    private fun status() {
        println("\nDica:                            ${jogoDaForca.getDica()}")
        println()
        println("Palavra:                         ${jogoDaForca.getPalavraOculta()}")
        println()
        println("Quantidade de letras:            ${jogoDaForca.getTamPalavra()}")
        println("Letras já utilizadas:           [${jogoDaForca.getLetrasUsadas()}]")
        println("Quantidade de letras distintas:  ${jogoDaForca.letraDistinta()}")

        // MUDANÇA DE ESTADO DE CORES DE ACORDO COM OS ACERTOS

        if (jogoDaForca.getAcertos() == 0) {
            println("${this.ANSI_RED}Quantidade de acertos:           ${jogoDaForca.getAcertos()}${this.ANSI_RESET}")
        }
        else if (jogoDaForca.getAcertos() == jogoDaForca.getTamPalavra()) {
            println("${this.ANSI_GREEN}Quantidade de acertos:           ${jogoDaForca.getAcertos()}${this.ANSI_RESET}")
        }
        else {
            println("${this.ANSI_YELLOW}Quantidade de acertos:           ${jogoDaForca.getAcertos()}${this.ANSI_RESET}")
        }

        // MUDANÇA DE ESTADO DE CORES DE ACORDO COM AS TENTATIVAS

        if (jogoDaForca.getTentativa() == jogoDaForca.getTamPalavra()) {
            println("${this.ANSI_GREEN}Quantidade de tentativas:        ${jogoDaForca.getTentativa()}${this.ANSI_RESET}")
        }
        else if (jogoDaForca.getTentativa() <= 1) {
            println("${this.ANSI_RED}Quantidade de tentativas:        ${jogoDaForca.getTentativa()}${this.ANSI_RESET}")
        }
        else {
            println("${this.ANSI_YELLOW}Quantidade de tentativas:        ${jogoDaForca.getTentativa()}${this.ANSI_RESET}")
        }

        println()
    }

    fun terminou(): Boolean {
        return jogoDaForca.terminou()
    }

    fun perdeu(): Boolean {
        return jogoDaForca.getTentativa() == 0
    }
}