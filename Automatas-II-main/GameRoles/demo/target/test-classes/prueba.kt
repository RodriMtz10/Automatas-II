fun main() {
    var vida = 150
    var monedas = 30
    var enemigos = 4
    var vivo = 1
    var ataque = 15
    var defensa = 3
    println("Estado inicial")
    println("Vida:" + vida)
    println("Monedas:" + monedas)
    println("Ataque:" + ataque)
    println("---- INICIO DE BATALLA ----")
    while (enemigos > 0) {
        if (vivo == 1) {
            println("Un enemigo aparece")
            ataque = ataque * 2
            println("Ataque aumentado:" + ataque)
            vida = vida - ataque
            println("El jugador recibe daño")
            println("Vida restante:" + vida)
            ataque = ataque / defensa
            println("Ataque ajustado:" + ataque)
            if (vida > 80) {
                println("Jugador en excelente estado")
            }
            if (vida < 1) {
                println("El jugador ha muerto")
                vivo = 0
            }
            enemigos = enemigos - 1
            println("Enemigos restantes:" + enemigos)
        }
    }
    println("---- ENTRENAMIENTO ----")
    if (vivo == 1) {
        var i = 0
        i = 0
        i = i + 1
        {
            i = 0
            while (i < 3) {
                vida = vida + 10
                monedas = monedas + 5
                ataque = ataque + 3
                println("---Entrenando...---")
                println("Vida:" + vida)
                println("Monedas:" + monedas)
                println("Ataque:" + ataque)
                if (ataque > 25) {
                    println("Ataque alto")
                }
                i = i + 1
            }
        }
        println("---- ESTADO FINAL ----")
        println("Vida final:" + vida)
        println("Monedas finales:" + monedas)
        println("Ataque final:" + ataque)
    }
    if (vivo < 1) {
        println("El jugador no sobrevivio")
    }
}
