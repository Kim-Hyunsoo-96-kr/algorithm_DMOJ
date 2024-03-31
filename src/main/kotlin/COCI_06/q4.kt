package COCI_06

import java.io.File

fun q4(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06/q4_input.txt"
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

    /** 시작 좌표 찾는 함수 */
    fun findStartPoint(map:List<MutableList<String>>):Pair<Int, Int>{
        map.forEachIndexed{ row, item ->
            val col = item.indexOf("S")
            if(col != -1) return row to col
        }
        error("invalid start point")
    }

    /** 범람 좌표 찾는 함수 */
    fun setFooledField(map:List<MutableList<String>>):MutableList<Pair<Int, Int>>{
        val result = mutableListOf<Pair<Int, Int>>()
        map.forEachIndexed { row, item ->
            item.forEachIndexed{ col, it ->
                if(it == "*") result.add(row to col)

            }
        }
        return result
    }

    val (maxRow, maxCol) = input[0].split(" ").map{ it.toIntOrNull() ?: error("invalid input") }
    val map = input.subList(1, input.lastIndex+1).map{
        it.trim().split("").subList(1, it.length+1).toMutableList()
    }
    var fooldedFieldBound = setFooledField(map)
    var queue = mutableListOf(findStartPoint(map))
    var count = 0
    while(queue.isNotEmpty()){
        val newQueue = mutableListOf<Pair<Int,Int>>()
        queue.forEach {
            val (row, col) = it
            if(map[row][col] == "D"){
                println(count)
                return
            }
            if((row+1 < maxRow) && (map[row+1][col] == "." || map[row+1][col] == "D")) newQueue.add(row+1 to col)
            if((row-1 >= 0) && (map[row-1][col] == "." || map[row-1][col] == "D")) newQueue.add(row-1 to col)
            if((col+1 < maxCol) && (map[row][col+1] == "." || map[row][col+1] == "D")) newQueue.add(row to col+1)
            if((col-1 >= 0) && (map[row][col-1] == "." || map[row][col-1] == "D")) newQueue.add(row to col-1)
        }

        queue = newQueue

        val nextFooldedFieldBound = mutableListOf<Pair<Int, Int>>()
        fooldedFieldBound.forEach { (row, col) ->
            if((row+1 < maxRow) && map[row+1][col] == ".") {
                nextFooldedFieldBound.add(row+1 to col)
                map[row+1][col] = "*"
            }
            if((row-1 >= 0) && map[row-1][col] == "."){
                nextFooldedFieldBound.add(row-1 to col)
                map[row-1][col] = "*"
            }
            if((col+1 < maxCol) && map[row][col+1] == "."){
                nextFooldedFieldBound.add(row to col+1)
                map[row][col+1] = "*"
            }
            if((col-1 >= 0) && map[row][col-1] == "."){
                nextFooldedFieldBound.add(row to col-1)
                map[row][col-1] = "*"
            }
        }
        fooldedFieldBound = nextFooldedFieldBound
        count++
    }
    println("KAKTUS")
}
