import kotlin.math.*

fun main(){
    var u = Array(31){Array(6){0.0} }
    val h = 0.1
    val g = 0.2
    for (i in 0..30){
        u[i][0] = sin(PI*i*h/3)
        u[i][5] = cos(2*PI*i*h)
    }
    for (j in 0..5){
        u[0][j] = 1- cos(j*g*PI/2)
        u[30][j] = j*g*j*g
    }
    u = simpleIteration(u,0.01)
    for (i in 0..30)
        for (j in 0..5)
            println("u( ${i*0.1.toInt()},${((i*0.1-i*0.1.toInt())*10).toInt()} ; ${j*0.2.toInt()},${((j*0.2-j*0.2.toInt())*10).toInt()} ) " +
                    "= ${u[i][j]}")
}

fun simpleIteration(u:Array<Array<Double>>,e:Double):Array<Array<Double>>{
    var a = u
    val b = u
    var c = MutableList(0){0.0}
    do {
        a = b
        c = MutableList(0){0.0}
        for (i in 1 until 30)
            for (j in 1 until 5) {
                b[i][j] =
                    (0.04 * (b[i - 1][j] + b[i + 1][j]) + 0.01 * (b[i][j - 1] + b[i][j + 1]) - 0.04 * 0.01 * cos(i * 0.01 + j * 0.02))
                c.add(abs(b[i][j] - a[i][j]))
            }
    }while (c.max()!!>e)
    return b

}