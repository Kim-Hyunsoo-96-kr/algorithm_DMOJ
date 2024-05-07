package COCI_06_05

import java.io.File

/**
 * 각 팀별로 5개의 상태가 주어짐
 * 상태 1: 총 경기 수
 * 상태 2: 이긴 경기 수
 * 상태 3: 비긴 경기 수
 * 상태 4: 진 경기 수
 * 상태 5: 총 승점
 * 승점: 이김 - 3, 비김 - 1, 짐 - 0
 *
 * 각 팀별로 ?를 포함한 상태가 주어졌을 때, ? 를 계산하여 완전한 상태를 복원해야함
 * 각 팀의 상태는 독립적임
 *
 * sample input 1
 * 5
 * 27 21 3 3 66
 * 27 18 6 3 ?
 * ? 15 5 7 50
 * ? 14 7 5 ?
 * ? 14 ? 8 47
 *
 * sample output 1
 * 27 21 3 3 66
 * 27 18 6 3 60  -> ? 인 상태 = 승점,    승점 = 18*3 + 6*1 = 60
 * 27 15 5 7 50  -> ? 인 상태 = 총 경기수,     총 경기수 = 15 + 5 + 7 = 27
 * 26 14 7 5 49  -> ? 인 상태 = 총 경기수, 승점,     총 경기수 = 14 + 7 + 5 = 26, 승점 = 14*3 + 7*1 = 49
 * 27 14 5 8 47  -> ? 인 상태 = 총 경기수, 비긴 횟수,      비긴 횟수 = (47 - 14*3)/1 = 5, 총 경기수 = 14 + 5 + 8 = 27
 *
 *
 * x : 승수, y : 비김수, z : 패수
 *
 * state case 1 - 총 경기수가 ? 인 경우
 * 1-1 승점이 ? 인 경우 - 무조건 승, 비김, 패의 상태는 알려줘야 유니크함 => 2,3,4 를 통해 계산
 * 1-2 승점이 ? 아닌 경우
 * 1-2-1 승점이 2이하인 경우 - 승수는 무조건 0, 따라서 패수는 무조건 알아야 유니크, 비김수 = 승점
 * 1-2-2 승점이 3이상인 경우
 * 1-2-2-1 승이 ? 인 경우 - 승수 = (승점 - 비김수) / 3, 경기수 = 승수 + 비김수 + 패수
 * 1-2-2-2 비김이 ? 인 경우 - 비김수 = 승점 - 3*승수, 경기수 = 승수 + 비김수 + 패수
 * 1-2-2-2 패가 ?인경우 - 유니크하지 않음
 *
 * state case 2 - 총 승점이 ? 인 경우
 * 2-1 총 경기수가 ? 인 경우 - 1-1의 case와 동일
 * 2-2 총 경기수가 ? 아닌 경우
 * 2-2-1 승이 ? 인 경우 - 승수 = 총 경기수 - 비김수 - 패수, 승점 = 3*승수 + 비김수
 * 2-2-2 비김이 ? 인 경우 - 비김수 = 총 경기수 - 승수 - 패수, 승점 = 3*승수 + 비김수
 * 2-2-3 패가 ? 인 경우 - 패수 = 총 경기수 - 승수 - 비김수, 승점 = 3*승수 + 비김수
 * 2-3 승, 비김, 패중 2가지가 ? 인경우 유니크하지 않으므로 해당 경우는 없음
 *
 * state case 3 - 경기수, 승점이 주어질 경우
 * 승수:x, 비김수:y, 패수:z 라 하면
 * 1) x+y+z = 경기수
 * 2) 3x+y = 승점
 * 1),2) 의 식을 연립하여 x,y,z를 구하면 된다.
 * 관계식이 2가지 밖에 없으므로 x,y,z중 적어도 하나는 ?가 아님
 * x,y,z 전부 ? 일 경우 승점이 3이상일경우 유니크하지 않으므로 해당 경우는 없음
 *
 * 3-1 승점이 2 이하인 경우 - 승수는 무조건 0, 비김수 = 승점, 패수 = 경기수 - 비김수
 * 3-2 승점이 3 이상인 경우
 * 3-2-1 승수가 ? 아닌 경우 - 비김수 = 승점 - 3*승수, 패수 = 경기수 - 승수 - 비김수
 * 3-2-2 비김수가 ? 아닌 경우 - 승수 = (승점 - 비김수) / 3, 패수 = 경기수 - 승수 - 비김수
 * 3-2-3 패수가 ? 아닌 경우 - 승수 = (승점 - 경기수 + 패수) / 2, 비김수 = 경기수 - 승수 - 패수
 *
 */

//enum class STATE(val idx:Int){
//    TOTAL(0),
//    WIN(1),
//    DRAW(2),
//    LOSE(3),
//    POINT(4)
//}

fun q4(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_05/q4_input.txt"
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

    var n:Int
    var teamStates:List<MutableList<Int>>

    input.trim().split("\n").also{ lines ->
        n = lines[0].toIntOrNull()?.also{ if(it<1 || it>1000) error("n: out of range") } ?: error("n: int casting fail")
        teamStates = lines.subList(1,lines.size).map{ stateLine ->
            stateLine.trim().split(" ").let{ state ->
                state.map{
                    if(it != "?") it.toIntOrNull() ?: error("invalid state")
                    else -1
                }
            }.toMutableList()
        }
    }

    teamStates.forEach { teamState ->
        if(teamState[0] == -1){
            /** case 1-1 */
            if(teamState[4] == -1){
                teamState[4] = 3*teamState[1] + teamState[2]
                teamState[0] = teamState[1] + teamState[2] + teamState[3]
            }else{
                if(teamState[4] < 3){
                    /** case 1-2-1 */
                    if(teamState[3] != -1){
                        teamState[1] = 0
                        teamState[2] = teamState[4]
                        teamState[0] = teamState[1] + teamState[2] + teamState[3]
                    }else{
                        error("not unique-1")
                    }
                }
                /** case 1-2-2 */
                else{
                    if(teamState[1] == -1 && teamState[2] != -1 && teamState[3] != -1){
                        teamState[1] = (teamState[4] - teamState[2])/3
                        teamState[0] = teamState[1] + teamState[2] + teamState[3]
                    }else if(teamState[1] != -1 && teamState[2] == -1 && teamState[3] != -1){
                        teamState[2] = teamState[4] - 3*teamState[1]
                        teamState[0] = teamState[1] + teamState[2] + teamState[3]
                    }else{
                        error("not unique-2")
                    }
                }
            }
        }else if(teamState[4] == -1){
            /** case 2-2-1 */
            if(teamState[1] == -1 && teamState[2] != -1 && teamState[3] != -1){
                teamState[1] = teamState[0] - teamState[2] - teamState[3]
                teamState[4] = 3*teamState[1] + teamState[2]
            }
            /** case 2-2-2 */
            else if(teamState[1] != -1 && teamState[2] == -1 && teamState[3] != -1){
                teamState[2] = teamState[0] - teamState[1] - teamState[3]
                teamState[4] = 3*teamState[1] + teamState[2]
            }
            /** case 2-2-3 */
            else if(teamState[1] != -1 && teamState[2] != -1 && teamState[3] == -1){
                teamState[3] = teamState[0] - teamState[1] - teamState[2]
                teamState[4] = 3*teamState[1] + teamState[2]
            }else{
                error("not unique-3")
            }
        }else{
            /** case 3-1 */
            if(teamState[4] < 3){
                teamState[1] = 0
                teamState[2] = teamState[4]
                teamState[3] = teamState[0] - teamState[2]
            }else{
                /** case 3-2-1 */
                if(teamState[1] != -1){
                    teamState[2] = teamState[4] - 3*teamState[1]
                    teamState[3] = teamState[0] - teamState[1] - teamState[2]
                /** case 3-2-2 */
                }else if(teamState[2] != -1){
                    teamState[1] = (teamState[4] - teamState[2]) / 3
                    teamState[3] = teamState[0] - teamState[1] - teamState[2]
                /** case 3-2-3 */
                }else if(teamState[3] != -1){
                    teamState[1] = (teamState[4] - teamState[0] + teamState[3]) / 2
                    teamState[2] = teamState[0] - teamState[1] - teamState[3]
                }else{
                    error("not unique-4")
                }
            }
        }
    }

    val output = teamStates.map{it.joinToString(" ")}.joinToString("\n")
    println(output)
}