package COCI_06

import java.io.File

fun q6(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06/q6_input.txt"
    val file = File(path)
    val stringBuilder = StringBuilder()
    try{
        file.forEachLine {
            stringBuilder.append(it)
            stringBuilder.append("\n")
        }
    }catch(e:Exception){
        println("파일을 읽는 도중 오류가 발생했습니다: ${e.message}")
    }
    val input = stringBuilder.trim().toString().split("\n")

    /** 입력에서 input 읽어오기 */
//    val bf = BufferedReader(InputStreamReader(System.`in`))
//    val input = bf.readText().trim()

    val (ROW, COL) = input[0].split(" ").map { it.toIntOrNull() ?: error("invalid input") }
    var maxSize = if(ROW > COL) COL else ROW
    val map = input.subList(1, input.lastIndex+1)

    for(size in maxSize downTo 2){
        for(row in 0..map.size - size){
            for(col in 0..map[0].length - size){
                if(isKiller(map, size, row, col)){
                    println(size)
                    return
                }
            }
        }
    }
    println(-1)
}
fun isKiller(map:List<String>, size:Int, row:Int, col:Int):Boolean{
    for(i in 0 until size){
        for(j in i until  size){
            if(map[row+i][col+j] != map[row+size-i-1][col+size-j-1]) return false
        }
    }
    return true
}