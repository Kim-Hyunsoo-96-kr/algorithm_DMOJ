package COCI_06_03

import java.io.*

fun q5(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_03/q4_input.txt"
    val file = File(path)
    val stringBuilder = StringBuilder()
    try {
        file.forEachLine {
            stringBuilder.append(it)
            stringBuilder.append("\n") // 각 줄 끝에 줄바꿈 문자 추가
        }
    } catch (e: Exception) {
        println("파일을 읽는 도중 오류가 발생했습니다: ${e.message}")
    }
    val input = stringBuilder.toString()
    /** 입력에서 input 읽어오기 */
//    val bf = BufferedReader(InputStreamReader(System.`in`))
//    val input = bf.readText().trim()

    var roads:MutableMap<Int, MutableList<Int>> = mutableMapOf()
    var isLoop:MutableList<Boolean>
    /** 초기화 로직 */
    input.trim().split("\n").also{ input ->
        /** town 갯수, road 갯수 설정 */
        val (town, roadNum) = input[0].split(" ").map { it.toIntOrNull() ?: error("town, roadNum int casting fail") }
        /** road 갯수와 input이 같은지 확인 */
        if(roadNum != input.size-1) error("not match road number")
        /** town 과 연결된 road 초기화 */
        for(i in 1..town) roads[i] = mutableListOf()
        input.subList(1, input.size-1).forEach {
            val(start, end) = it.split(" ")
                .map { it.toIntOrNull() ?: error("start, end int casting fail") }
                /** start, end town이 town의 범위를 넘어가면 에러 */
                .also{ towns -> if(towns.any { it < 1 || it > town}) error("start, end town out of range")}
            /** town 과 연결된 road 설정 */
            roads[start]!!.add(end)
        }
        /** isLoop 초기화 */
        isLoop = MutableList(town){ false }
    }

    val queue:MutableList<Pair<Int, MutableList<Int>>> = mutableListOf(1 to mutableListOf())

    while(queue.isNotEmpty()){
        val town = queue.removeFirst()

        val nextTowns = roads[town.first] ?: error("roads not found")

        nextTowns.forEach { nextTown ->
            /** nextTown이 이때까지 이동한 town에 존재하는지 확인 */
            val idx = town.second.indexOf(nextTown)
            /** nextTown이 이때까지 이동한 town에 존재하는 경우 */
            if(idx != -1){
                /** isLoop의 해당 town을 true로 변경 */
                town.second.subList(idx, town.second.lastIndex).forEach { isLoop[it] = true }
            } else{
                val new = nextTown to mutableListOf(town.second)
            }
        }
    }
}