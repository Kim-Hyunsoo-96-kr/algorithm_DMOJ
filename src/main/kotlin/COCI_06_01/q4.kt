package COCI_06_01

import java.io.File

fun q4(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_01/q4_input.txt"
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

    /** 주어진 map의 크기(maxRow, maxCol)를 저장 */
    val (maxRow, maxCol) = input[0].split(" ").map{ it.toIntOrNull() ?: error("invalid input") }

    /** 주어진 map을 2차원 리스트로 파싱 */
    val map = input.subList(1, input.lastIndex+1).map{
        it.trim().split("").subList(1, it.length+1).toMutableList()
    }

    /** 범람 지역의 좌표를 담는 큐 세팅 */
    var fooldedFieldBound = setFooledField(map)

    /** 고슴도치가 있을 수 있는 좌표의 큐 세팅 */
    var queue = mutableListOf(findStartPoint(map))

    /** 시행 횟수 초기화 */
    var count = 0

    /** 고슴도치가 있을 수 있는 좌표의 큐가 비어있을 때 까지 순회 */
    while(queue.isNotEmpty()){
        /** 고슴 도치의 가능한 다음 위치의 좌표르 담는 큐 세팅 */
        val newQueue = mutableListOf<Pair<Int,Int>>()
        /** 고슴도치 큐 순회 */
        queue.forEach {
            val (row, col) = it
            /** 고슴도치의 현재 좌표가 목적지면 이때까지의 시행횟수를 출력하고 리턴 */
            if(map[row][col] == "D"){
                println(count)
                return
            }
            /** 고슴도치가 이미 지나온 길을 "," 로 표시 */
            map[row][col] = ","

            /** 고슴도치가 갈 수 있는 다음 위치를 체크하여 다음 위치의 좌표를 담는 newQueue에 삽입 */
            if((row+1 < maxRow) && (map[row+1][col] == "." || map[row+1][col] == "D")) newQueue.add(row+1 to col)
            if((row-1 >= 0) && (map[row-1][col] == "." || map[row-1][col] == "D")) newQueue.add(row-1 to col)
            if((col+1 < maxCol) && (map[row][col+1] == "." || map[row][col+1] == "D")) newQueue.add(row to col+1)
            if((col-1 >= 0) && (map[row][col-1] == "." || map[row][col-1] == "D")) newQueue.add(row to col-1)
        }

        /** 고슴도치가 있을 수 있는 좌표의 큐를 newQueue로 치환 */
        queue = newQueue

        /** 범람지역으로 변할 수 있는 다음 위치의 좌표를 담는 큐 */
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
