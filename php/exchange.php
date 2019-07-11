<?php
$response = array ();
$jsonObjPar = file_get_contents ( 'php://input' );
if (! empty ( $jsonObjPar )) {
	$jsonObj = json_decode ( $jsonObjPar, true );
	if (! empty ( $jsonObj )) {
		if (isset ( $jsonObj ["type"] )) {
			switch ($jsonObj ["type"]) {
				case 1 :
					include 'query/sp_tool_q1.php';
					break;
				case 2 :
					include 'query/sp_tool_q2.php';
					break;
				case 3 :
					include 'query/sp_tool_q3.php';
					break;
				case 4 :
					include 'query/sp_tool_q4.php';
					break;
				case 5 :
					include 'query/sp_tool_q5.php';
					break;
				case 6 :
					include 'query/sp_tool_q6.php';
					break;
				case 7 :
					include 'query/sp_tool_q7.php';
					break;
				case 8 :
					include 'query/sp_tool_q8.php';
					break;
				case 9 :
					include 'query/sp_tool_q9.php';
					break;
				case 10 :
					include 'query/sp_tool_q10.php';
					break;
				case 11 :
					include 'query/sp_tool_q11.php';
					break;
				case 12 :
					include 'query/sp_tool_q12.php';
					break;
				case 13 :
					include 'query/sp_tool_q13.php';
					break;
				case 14 :
					include 'query/sp_tool_q14.php';
					break;
				case 15 :
					include 'query/sp_tool_q15.php';
					break;
				case 16 :
					include 'query/sp_tool_q16.php';
					break;
				case 17 :
					include 'query/sp_tool_q17.php';
					break;
				case 18 :
					include 'query/sp_tool_q18.php';
					break;
				case 19 :
					include 'query/sp_tool_q19.php';
					break;
				case 20 :
					include 'query/sp_tool_q20.php';
					break;
				case 21 :
					include 'query/sp_tool_q21.php';
					break;
				case 22 :
					include 'query/sp_tool_q22.php';
					break;
				case 23 :
					include 'query/sp_tool_q23.php';
					break;
				case 24 :
					include 'query/sp_tool_q24.php';
					break;
				case 25 :
					include 'query/sp_tool_q25.php';
					break;
				case 26 :
					include 'query/sp_tool_q26.php';
					break;
				case 27 :
					include 'query/sp_tool_q27.php';
					break;
				case 28 :
					include 'query/sp_tool_q28.php';
					break;
				case 29 :
					include 'query/sp_tool_q29.php';
					break;
				case 30 :
					include 'query/sp_tool_q30.php';
					break;
			}
		} else {
			$response ["status"] = 3;
		}
	} else {
		$response ["status"] = 2;
	}
} else {
	$response ["status"] = 1;
}
echo json_encode ( $response );
?>