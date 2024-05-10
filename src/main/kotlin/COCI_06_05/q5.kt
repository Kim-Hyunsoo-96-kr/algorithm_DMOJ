package COCI_06_05

import java.io.File

/**
 * 게임의 규칙
 * 1) 두명의 플레이어가 게임을 진행, 첫번째 순서의 플레이어가 숫자를 선택
 * 2) 다음 플레이어는 이전의 플레이어가 선택한 숫자에 인접한 숫자 2개중 하나를 선택
 * 3) 1,2 과정을 숫자가 다 떨어질때까지 반복
 * 4) 3과정이 끝나면 두플레이어가 가지고있는 카드를 확인
 * 5) 홀수 카드를 더 많이 가진 플레이어가 승리
 *
 * 문제 요구 사항
 * 첫번째 플레이어가 이길 수 있는 첫번째 카드 선택의 경우의 수를 구하시오
 *
 * sample input 1
 * 3
 * 3 1 5
 *
 * p1 - 3, p2 - 1, p1 - 5 => p1(3,5), p2(1) : p1 win
 *         p2 - 5, p1 - 1 => p1(3,5), p2(1) : p1 win
 * p1 - 1, p2 - 3, p1 - 5 => p1(3,5), p2(1) : p1 win
 *         p2 - 5, p1 - 3 => p1(3,5), p2(1) : p1 win
 * p1 - 5, p2 - 1, p1 - 3 => p1(3,5), p2(1) : p1 win
 *         p2 - 3, p1 - 1 => p1(3,5), p2(1) : p1 win
 *
 * 따라서 p1이 이길 수 있는 카드 선택 경우는 3,1,5 - 3가지
 *
 * sample input 2
 * 4
 * 1 2 3 4
 *
 * p1 - 1, p2 - 2, p1 - 3, p2 - 4 => p1(1,3), p2(2,4) : p1 win
 *                 p1 - 4, p2 - 3 => p1(1,4), p2(2,3) : draw
 *         p2 - 4, p1 - 2, p2 - 3 => p1(1,2), p2(3,4) : draw
 *                 p1 - 3, p2 - 2 => p1(1,3), p2(2,4) : p1 win
 * p1 - 2, p2 - 1, p1 - 3, p2 - 4 => p1(2,3), p2(1,4) : draw
 *                 p1 - 4, p2 - 3 => p1(2,4), p2(1,3) : p1 lose
 *         p2 - 3, p1 - 1, p2 - 4 => p1(1,2), p2(3,4) : draw
 *                 p1 - 4, p2 - 1 => p1(2,4), p2(1,3) : p1 lose
 * p1 - 3, p2 - 2, p1 - 1, p2 - 4 => p1(1,3), p2(2,4) : p1 win
 *                 p1 - 4, p2 - 1 => p1(3,4), p2(1,2) : draw
 *         p2 - 4, p1 - 1, p2 - 2 => p1(1,3), p2(2,4) : p1 win
 *                 p1 - 2, p2 - 1 => p1(2,3), p2(1,4) : draw
 * p1 - 4, p2 - 1, p1 - 2, p2 - 3 => p1(2,4), p2(1,3) : p1 lose
 *                 p1 - 3, p2 - 2 => p1(3,4), p2(1,2) : draw
 *         p2 - 3, p1 - 1, p2 - 2 => p1(1,4), p2(2,3) : draw
 *                 p1 - 2, p2 - 1 => p1(2,4), p2(1,3) : p1 lose
 *
 * 따라서 p1이 이길 수 있는 카드 선택 경우는 1,3 - 2가지
 */
fun q5(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_05/q5_input.txt"
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
}