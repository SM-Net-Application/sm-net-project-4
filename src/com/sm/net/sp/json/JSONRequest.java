package com.sm.net.sp.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.sm.net.sp.model.Week;
import com.sm.net.util.enumeration.JSONStatus;

/**
 * Gestione delle richieste JSON Un JSON-Object viene creato con un indice: type
 * type --> determina il tipo di richiesta In base alla richiesta vengono
 * aggiunte le informazioni necessarie
 * 
 * <li>1 - Conta il numero di utenti</li>
 * <li>2 - Inserisci utente</li>
 * <li>3 - Verifica utente</li>
 * <li>4 - Elenco degli utenti</li>
 * <li>5 - Aggiorna ruoli utente</li>
 * <li>6 - Elimina utente</li>
 * <li>7 - Verifica nome utente</li>
 * <li>8 - Inserisci utente root</li>
 * <li>9 - Elenco dei componenti</li>
 * <li>10 - Inserisci componente</li>
 * <li>11 - Aggiorna componente</li>
 * <li>12 - Elimina componente</li>
 * 
 * @author SM-Net
 *
 */
public class JSONRequest {

	public static JSONObject GET_COUNT_USERS() {
		return create(Integer.valueOf(1));
	}

	public static JSONObject RUN_INIT(String userEncrypted, String passwordEncrypted) {
		JSONObject jsonObj = create(Integer.valueOf(2));
		jsonObj.put("user", userEncrypted);
		jsonObj.put("password", passwordEncrypted);
		return jsonObj;
	}

	public static JSONObject CHECK_USER(String userEncrypted, String passwordEncrypted) {
		JSONObject jsonObj = create(Integer.valueOf(3));
		jsonObj.put("user", userEncrypted);
		jsonObj.put("password", passwordEncrypted);
		return jsonObj;
	}

	public static JSONObject GET_ALL_USERS() {
		return create(Integer.valueOf(4));
	}

	public static JSONObject INSERT_NEW_USER(String userEncrypted, String passwordEncrypted) {
		JSONObject jsonObj = create(Integer.valueOf(2));
		jsonObj.put("user", userEncrypted);
		jsonObj.put("password", passwordEncrypted);
		return jsonObj;
	}

	public static JSONObject UPDATE_USER_RULES(String spUserID, String spInf1, String spInf2, String spInf3,
			String spInf4) {

		JSONObject jsonObj = create(Integer.valueOf(5));
		jsonObj.put("spUserID", spUserID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		return jsonObj;
	}

	public static JSONObject DELETE_USER(String spUserID) {
		JSONObject jsonObj = create(Integer.valueOf(6));
		jsonObj.put("spUserID", spUserID);
		return jsonObj;
	}

	public static JSONObject CHECK_USERNAME(String spUserName) {
		JSONObject jsonObj = create(Integer.valueOf(7));
		jsonObj.put("spUserName", spUserName);
		return jsonObj;
	}

	public static JSONObject INSERT_ROOT_USER(String userEncrypted, String passwordEncrypted) {
		JSONObject jsonObj = create(Integer.valueOf(8));
		jsonObj.put("user", userEncrypted);
		jsonObj.put("password", passwordEncrypted);
		return jsonObj;
	}

	public static JSONObject GET_ALL_MEMBERS() {
		return create(Integer.valueOf(9));
	}

	public static JSONObject INSERT_MEMBER(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, String spInf30,
			String spInf31, String spInf32, String spInf33, String spInf34, String spInf35, String spInf36,
			String spInf37, String spInf38, String spInf39, String spInf40, String spInf41, String spInf42,
			String spInf43, String spInf44, String spInf45, String spInf46) {

		JSONObject jsonObj = create(Integer.valueOf(10));
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf6", spInf6);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
		jsonObj.put("spInf9", spInf9);
		jsonObj.put("spInf10", spInf10);
		jsonObj.put("spInf11", spInf11);
		jsonObj.put("spInf12", spInf12);
		jsonObj.put("spInf13", spInf13);
		jsonObj.put("spInf14", spInf14);
		jsonObj.put("spInf15", spInf15);
		jsonObj.put("spInf16", spInf16);
		jsonObj.put("spInf17", spInf17);
		jsonObj.put("spInf18", spInf18);
		jsonObj.put("spInf19", spInf19);
		jsonObj.put("spInf20", spInf20);
		jsonObj.put("spInf21", spInf21);
		jsonObj.put("spInf22", spInf22);
		jsonObj.put("spInf23", spInf23);
		jsonObj.put("spInf24", spInf24);
		jsonObj.put("spInf25", spInf25);
		jsonObj.put("spInf26", spInf26);
		jsonObj.put("spInf27", spInf27);
		jsonObj.put("spInf28", spInf28);
		jsonObj.put("spInf29", spInf29);
		jsonObj.put("spInf30", spInf30);
		jsonObj.put("spInf31", spInf31);
		jsonObj.put("spInf32", spInf32);
		jsonObj.put("spInf33", spInf33);
		jsonObj.put("spInf34", spInf34);
		jsonObj.put("spInf35", spInf35);
		jsonObj.put("spInf36", spInf36);
		jsonObj.put("spInf37", spInf37);
		jsonObj.put("spInf38", spInf38);
		jsonObj.put("spInf39", spInf39);
		jsonObj.put("spInf40", spInf40);
		jsonObj.put("spInf41", spInf41);
		jsonObj.put("spInf42", spInf42);
		jsonObj.put("spInf43", spInf43);
		jsonObj.put("spInf44", spInf44);
		jsonObj.put("spInf45", spInf45);
		jsonObj.put("spInf46", spInf46);

		return jsonObj;
	}

	public static JSONObject UPDATE_MEMBER(String spMemberID, String spInf1, String spInf2, String spInf3,
			String spInf4, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11,
			String spInf12, String spInf13, String spInf14, String spInf15, String spInf16, String spInf17,
			String spInf18, String spInf19, String spInf20, String spInf21, String spInf22, String spInf23,
			String spInf24, String spInf25, String spInf26, String spInf27, String spInf28, String spInf29,
			String spInf30, String spInf31, String spInf32, String spInf33, String spInf34, String spInf35,
			String spInf36, String spInf37, String spInf38, String spInf39, String spInf40, String spInf41,
			String spInf42, String spInf43, String spInf44, String spInf45, String spInf46) {

		JSONObject jsonObj = create(Integer.valueOf(11));
		jsonObj.put("spMemberID", spMemberID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf6", spInf6);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
		jsonObj.put("spInf9", spInf9);
		jsonObj.put("spInf10", spInf10);
		jsonObj.put("spInf11", spInf11);
		jsonObj.put("spInf12", spInf12);
		jsonObj.put("spInf13", spInf13);
		jsonObj.put("spInf14", spInf14);
		jsonObj.put("spInf15", spInf15);
		jsonObj.put("spInf16", spInf16);
		jsonObj.put("spInf17", spInf17);
		jsonObj.put("spInf18", spInf18);
		jsonObj.put("spInf19", spInf19);
		jsonObj.put("spInf20", spInf20);
		jsonObj.put("spInf21", spInf21);
		jsonObj.put("spInf22", spInf22);
		jsonObj.put("spInf23", spInf23);
		jsonObj.put("spInf24", spInf24);
		jsonObj.put("spInf25", spInf25);
		jsonObj.put("spInf26", spInf26);
		jsonObj.put("spInf27", spInf27);
		jsonObj.put("spInf28", spInf28);
		jsonObj.put("spInf29", spInf29);
		jsonObj.put("spInf30", spInf30);
		jsonObj.put("spInf31", spInf31);
		jsonObj.put("spInf32", spInf32);
		jsonObj.put("spInf33", spInf33);
		jsonObj.put("spInf34", spInf34);
		jsonObj.put("spInf35", spInf35);
		jsonObj.put("spInf36", spInf36);
		jsonObj.put("spInf37", spInf37);
		jsonObj.put("spInf38", spInf38);
		jsonObj.put("spInf39", spInf39);
		jsonObj.put("spInf40", spInf40);
		jsonObj.put("spInf41", spInf41);
		jsonObj.put("spInf42", spInf42);
		jsonObj.put("spInf43", spInf43);
		jsonObj.put("spInf44", spInf44);
		jsonObj.put("spInf45", spInf45);
		jsonObj.put("spInf46", spInf46);

		return jsonObj;
	}

	public static JSONObject DELETE_MEMBER(String spMemberID) {
		JSONObject jsonObj = create(Integer.valueOf(12));
		jsonObj.put("spMemberID", spMemberID);
		return jsonObj;
	}

	public static JSONObject INSERT_FAMILY(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String idToRemove, String idToSet) {
		JSONObject jsonObj = create(Integer.valueOf(13));
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf6", spInf6);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
		jsonObj.put("idToRemove", idToRemove);
		jsonObj.put("idToSet", idToSet);
		return jsonObj;
	}

	public static JSONObject GET_ALL_FAMILIES() {
		return create(Integer.valueOf(14));
	}

	public static JSONObject UPDATE_FAMILY(String spFamID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf7, String spInf8, String idToRemove, String idToSet) {

		JSONObject jsonObj = create(Integer.valueOf(15));
		jsonObj.put("spFamID", spFamID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
		jsonObj.put("idToRemove", idToRemove);
		jsonObj.put("idToSet", idToSet);
		return jsonObj;
	}

	public static JSONObject DELETE_FAMILY(String spFamilyID) {

		JSONObject jsonObj = create(Integer.valueOf(16));
		jsonObj.put("spFamilyID", spFamilyID);
		return jsonObj;
	}

	public static JSONObject INSERT_SERGROUP(String spInf1, String spInf2, String spInf3, String idToRemove,
			String idToSet) {
		JSONObject jsonObj = create(Integer.valueOf(17));
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("idToRemove", idToRemove);
		jsonObj.put("idToSet", idToSet);
		return jsonObj;
	}

	public static JSONObject GET_ALL_SERGROUPS() {
		return create(Integer.valueOf(18));
	}

	public static JSONObject UPDATE_SERGROUP(String spSerGrID, String spInf1, String spInf2, String spInf3,
			String idToRemove, String idToSet) {

		JSONObject jsonObj = create(Integer.valueOf(19));
		jsonObj.put("spSerGrID", spSerGrID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("idToRemove", idToRemove);
		jsonObj.put("idToSet", idToSet);
		return jsonObj;
	}

	public static JSONObject DELETE_SERGROUP(String spSerGrID) {

		JSONObject jsonObj = create(Integer.valueOf(20));
		jsonObj.put("spSerGrID", spSerGrID);
		return jsonObj;
	}

	public static JSONObject GET_ALL_WEEKS(Week weekStart, Week weekEnd) {
		JSONObject jsonObj = create(Integer.valueOf(21));
		jsonObj.put("keyStart", weekStart.getKey());
		jsonObj.put("keyEnd", weekEnd.getKey());
		return jsonObj;
	}

	public static JSONObject CHECK_INFO(String key) {
		JSONObject jsonObj = create(Integer.valueOf(22));
		jsonObj.put("keyInf", key);
		return jsonObj;
	}

	public static JSONObject INSERT_INFO(String key, String value) {
		JSONObject jsonObj = create(Integer.valueOf(23));
		jsonObj.put("keyInf", key);
		jsonObj.put("inf", value);
		return jsonObj;
	}

	public static JSONObject UPDATE_INFO(String key, String value) {
		JSONObject jsonObj = create(Integer.valueOf(24));
		jsonObj.put("keyInf", key);
		jsonObj.put("inf", value);
		return jsonObj;
	}

	public static JSONObject GET_INFO(String select) {
		JSONObject jsonObj = create(Integer.valueOf(25));
		jsonObj.put("select", select);
		return jsonObj;
	}

	public static JSONObject INSERT_WEEK(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInfMinistryParts,
			String spInfChristiansParts) {

		JSONObject jsonObj = create(Integer.valueOf(26));
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf6", spInf6);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
		jsonObj.put("spInf9", spInf9);
		jsonObj.put("spInf10", spInf10);
		jsonObj.put("spInf11", spInf11);
		jsonObj.put("spInf12", spInf12);
		jsonObj.put("spInf13", spInf13);
		jsonObj.put("spInf14", spInf14);
		jsonObj.put("spInf15", spInf15);
		jsonObj.put("spInf16", spInf16);
		jsonObj.put("spInf17", spInf17);
		jsonObj.put("spInf18", spInf18);
		jsonObj.put("spInf19", spInf19);
		jsonObj.put("spInf20", spInf20);
		jsonObj.put("spInf21", spInf21);
		jsonObj.put("spInf22", spInf22);
		jsonObj.put("spInf23", spInf23);
		jsonObj.put("spInf24", spInf24);
		jsonObj.put("spInf25", spInf25);
		jsonObj.put("spInf26", spInf26);
		jsonObj.put("spInf27", spInf27);
		jsonObj.put("spInf28", spInf28);
		jsonObj.put("spInfMP", spInfMinistryParts);
		jsonObj.put("spInfCP", spInfChristiansParts);

		return jsonObj;
	}

	public static JSONObject UPDATE_WEEK(String spWeekID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11,
			String spInf12, String spInf13, String spInf14, String spInf15, String spInf16, String spInf17,
			String spInf18, String spInf19, String spInf20, String spInf21, String spInf22, String spInf23,
			String spInf24, String spInf25, String spInf26, String spInf27, String spInf28, String spInfMinistryParts,
			String spInfChristiansParts) {

		JSONObject jsonObj = create(Integer.valueOf(27));

		jsonObj.put("spWeekID", spWeekID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf6", spInf6);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
		jsonObj.put("spInf9", spInf9);
		jsonObj.put("spInf10", spInf10);
		jsonObj.put("spInf11", spInf11);
		jsonObj.put("spInf12", spInf12);
		jsonObj.put("spInf13", spInf13);
		jsonObj.put("spInf14", spInf14);
		jsonObj.put("spInf15", spInf15);
		jsonObj.put("spInf16", spInf16);
		jsonObj.put("spInf17", spInf17);
		jsonObj.put("spInf18", spInf18);
		jsonObj.put("spInf19", spInf19);
		jsonObj.put("spInf20", spInf20);
		jsonObj.put("spInf21", spInf21);
		jsonObj.put("spInf22", spInf22);
		jsonObj.put("spInf23", spInf23);
		jsonObj.put("spInf24", spInf24);
		jsonObj.put("spInf25", spInf25);
		jsonObj.put("spInf26", spInf26);
		jsonObj.put("spInf27", spInf27);
		jsonObj.put("spInf28", spInf28);
		jsonObj.put("spInfMP", spInfMinistryParts);
		jsonObj.put("spInfCP", spInfChristiansParts);

		return jsonObj;
	}

	public static JSONStatus getStatus(JSONObject jsonObject) {
		int status = -1;
		if (jsonObject != null) {
			try {
				status = jsonObject.getInt("status");
			} catch (JSONException e) {
			}
		}
		return JSONStatus.getFromId(Integer.valueOf(status));
	}

	public static boolean isRequestOK(JSONObject jsonObject) {
		return isRequestOK(JSONRequest.getStatus(jsonObject));
	}

	public static boolean isRequestOK(JSONStatus jsonStatus) {
		boolean status = false;
		switch (jsonStatus) {
		case OK:
			status = true;
			break;
		default:
			// System.out.println(jsonStatus.getText());
			break;
		}
		return status;
	}

	private static JSONObject create(Integer type) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", type.intValue());
		return jsonObj;
	}

}
