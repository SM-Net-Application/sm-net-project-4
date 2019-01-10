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