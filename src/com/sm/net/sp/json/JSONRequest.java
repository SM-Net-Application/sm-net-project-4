package com.sm.net.sp.json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.crypto.SecretKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sm.net.sp.Meta;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.PDFDest;
import com.sm.net.sp.model.PDFReplace;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.PostImportDoc;
import com.sm.net.sp.model.PostImportNews;
import com.sm.net.sp.model.PostNews;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.model.TerritoryMap;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryRegistryEntity;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.util.Crypt;
import com.sm.net.util.enumeration.JSONStatus;

import javafx.collections.ObservableList;

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
			String spInf4, String spInf5, String spInf6, String spInf7, String spInf8) {

		JSONObject jsonObj = create(Integer.valueOf(5));
		jsonObj.put("spUserID", spUserID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf6", spInf6);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
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
			String spInf43, String spInf44, String spInf45, String spInf46, String spInf47, String spInf48,
			String spInf49, String spInf50, String spInf51, String spInf52, String spInf53, String spInf54,
			String spInf55, String spInf56, String spInf57, String spInf58, String spInf59, String spInf60,
			String spInf61, String spInf62, String spInf63, String spInf64, String spInf65, String spInf66,
			String spInf67, String spInf68, String spInf69, String spInf70, String spInf71, String spInf72,
			String spInf73, String spInf74, String spInf75) {

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
		jsonObj.put("spInf47", spInf47);
		jsonObj.put("spInf48", spInf48);
		jsonObj.put("spInf49", spInf49);
		jsonObj.put("spInf50", spInf50);
		jsonObj.put("spInf51", spInf51);
		jsonObj.put("spInf52", spInf52);
		jsonObj.put("spInf53", spInf53);
		jsonObj.put("spInf54", spInf54);
		jsonObj.put("spInf55", spInf55);
		jsonObj.put("spInf56", spInf56);
		jsonObj.put("spInf57", spInf57);
		jsonObj.put("spInf58", spInf58);
		jsonObj.put("spInf59", spInf59);
		jsonObj.put("spInf60", spInf60);
		jsonObj.put("spInf61", spInf61);
		jsonObj.put("spInf62", spInf62);

		jsonObj.put("spInf63", spInf63);
		jsonObj.put("spInf64", spInf64);
		jsonObj.put("spInf65", spInf65);
		jsonObj.put("spInf66", spInf66);
		jsonObj.put("spInf67", spInf67);
		jsonObj.put("spInf68", spInf68);
		jsonObj.put("spInf69", spInf69);
		jsonObj.put("spInf70", spInf70);
		jsonObj.put("spInf71", spInf71);
		jsonObj.put("spInf72", spInf72);
		jsonObj.put("spInf73", spInf73);
		jsonObj.put("spInf74", spInf74);
		jsonObj.put("spInf75", spInf75);

		return jsonObj;
	}

	public static JSONObject UPDATE_MEMBER(String spMemberID, String spInf1, String spInf2, String spInf3,
			String spInf4, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11,
			String spInf12, String spInf13, String spInf14, String spInf15, String spInf16, String spInf17,
			String spInf18, String spInf19, String spInf20, String spInf21, String spInf22, String spInf23,
			String spInf24, String spInf25, String spInf26, String spInf27, String spInf28, String spInf29,
			String spInf30, String spInf31, String spInf32, String spInf33, String spInf34, String spInf35,
			String spInf36, String spInf37, String spInf38, String spInf39, String spInf40, String spInf41,
			String spInf42, String spInf43, String spInf44, String spInf45, String spInf46, String spInf47,
			String spInf48, String spInf49, String spInf50, String spInf51, String spInf52, String spInf53,
			String spInf54, String spInf55, String spInf56, String spInf57, String spInf58, String spInf59,
			String spInf60, String spInf61, String spInf62, String spInf63, String spInf64, String spInf65,
			String spInf66, String spInf67, String spInf68, String spInf69, String spInf70, String spInf71,
			String spInf72, String spInf73, String spInf74, String spInf75) {

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
		jsonObj.put("spInf47", spInf47);
		jsonObj.put("spInf48", spInf48);
		jsonObj.put("spInf49", spInf49);
		jsonObj.put("spInf50", spInf50);
		jsonObj.put("spInf51", spInf51);
		jsonObj.put("spInf52", spInf52);
		jsonObj.put("spInf53", spInf53);
		jsonObj.put("spInf54", spInf54);
		jsonObj.put("spInf55", spInf55);
		jsonObj.put("spInf56", spInf56);
		jsonObj.put("spInf57", spInf57);
		jsonObj.put("spInf58", spInf58);
		jsonObj.put("spInf59", spInf59);
		jsonObj.put("spInf60", spInf60);
		jsonObj.put("spInf61", spInf61);
		jsonObj.put("spInf62", spInf62);

		jsonObj.put("spInf63", spInf63);
		jsonObj.put("spInf64", spInf64);
		jsonObj.put("spInf65", spInf65);
		jsonObj.put("spInf66", spInf66);
		jsonObj.put("spInf67", spInf67);
		jsonObj.put("spInf68", spInf68);
		jsonObj.put("spInf69", spInf69);
		jsonObj.put("spInf70", spInf70);
		jsonObj.put("spInf71", spInf71);
		jsonObj.put("spInf72", spInf72);
		jsonObj.put("spInf73", spInf73);
		jsonObj.put("spInf74", spInf74);
		jsonObj.put("spInf75", spInf75);

		return jsonObj;
	}

	public static JSONObject DELETE_MEMBER(String spMemberID) {
		JSONObject jsonObj = create(Integer.valueOf(12));
		jsonObj.put("spMemberID", spMemberID);
		return jsonObj;
	}

	public static JSONObject INSERT_FAMILY(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String idToRemove,
			String idToSet) {
		JSONObject jsonObj = create(Integer.valueOf(13));
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
		jsonObj.put("idToRemove", idToRemove);
		jsonObj.put("idToSet", idToSet);
		return jsonObj;
	}

	public static JSONObject GET_ALL_FAMILIES() {
		return create(Integer.valueOf(14));
	}

	public static JSONObject UPDATE_FAMILY(String spFamID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf7, String spInf8, String spInf9, String spInf10, String idToRemove,
			String idToSet) {

		JSONObject jsonObj = create(Integer.valueOf(15));
		jsonObj.put("spFamID", spFamID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf7", spInf7);
		jsonObj.put("spInf8", spInf8);
		jsonObj.put("spInf9", spInf9);
		jsonObj.put("spInf10", spInf10);
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
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, String spInf30,
			String spInf31, String spInf32, String spInf33, String spInf34, String spInf35, String spInf36,
			String spInf37, String spInf38, String spInf39, String spInf40, int spInf41, String spInf42, String spInf43,
			int spInf44, int spInf45, int spInf46, int spInf47, int spInf48, int spInf49, String spInf50, int spInf51,
			String spInf52, int spInf53, String spInf54, String spInf55, int spInf56, int spInf57, int spInf58,
			String spInf59, String spInf60, String spInf61, String spInf62, String spInf63, String spInf64, int spInf65,
			int spInf66, String spInfMinistryParts, String spInfChristiansParts) {

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
		jsonObj.put("spInf47", spInf47);
		jsonObj.put("spInf48", spInf48);
		jsonObj.put("spInf49", spInf49);
		jsonObj.put("spInf50", spInf50);
		jsonObj.put("spInf51", spInf51);
		jsonObj.put("spInf52", spInf52);
		jsonObj.put("spInf53", spInf53);

		jsonObj.put("spInf54", spInf54);
		jsonObj.put("spInf55", spInf55);
		jsonObj.put("spInf56", spInf56);
		jsonObj.put("spInf57", spInf57);
		jsonObj.put("spInf58", spInf58);

		jsonObj.put("spInf59", spInf59);
		jsonObj.put("spInf60", spInf60);
		jsonObj.put("spInf61", spInf61);
		jsonObj.put("spInf62", spInf62);
		jsonObj.put("spInf63", spInf63);
		jsonObj.put("spInf64", spInf64);
		jsonObj.put("spInf65", spInf65);
		jsonObj.put("spInf66", spInf66);

		jsonObj.put("spInfMP", spInfMinistryParts);
		jsonObj.put("spInfCP", spInfChristiansParts);

		return jsonObj;
	}

	public static JSONObject UPDATE_WEEK(String spWeekID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11,
			String spInf12, String spInf13, String spInf14, String spInf15, String spInf16, String spInf17,
			String spInf18, String spInf19, String spInf20, String spInf21, String spInf22, String spInf23,
			String spInf24, String spInf25, String spInf26, String spInf27, String spInf28, String spInf29,
			String spInf30, String spInf31, String spInf32, String spInf33, String spInf34, String spInf35,
			String spInf36, String spInf37, String spInf38, String spInf39, String spInf40, int spInf41, String spInf42,
			String spInf43, int spInf44, int spInf45, int spInf46, int spInf47, int spInf48, int spInf49,
			String spInf50, int spInf51, String spInf52, int spInf53, String spInf54, String spInf55, int spInf56,
			int spInf57, int spInf58, String spInf59, String spInf60, String spInf61, String spInf62, String spInf63,
			String spInf64, int spInf65, int spInf66, String spInfMinistryParts, String spInfChristiansParts) {

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
		jsonObj.put("spInf47", spInf47);
		jsonObj.put("spInf48", spInf48);
		jsonObj.put("spInf49", spInf49);
		jsonObj.put("spInf50", spInf50);
		jsonObj.put("spInf51", spInf51);
		jsonObj.put("spInf52", spInf52);
		jsonObj.put("spInf53", spInf53);

		jsonObj.put("spInf54", spInf54);
		jsonObj.put("spInf55", spInf55);
		jsonObj.put("spInf56", spInf56);
		jsonObj.put("spInf57", spInf57);
		jsonObj.put("spInf58", spInf58);

		jsonObj.put("spInf59", spInf59);
		jsonObj.put("spInf60", spInf60);
		jsonObj.put("spInf61", spInf61);
		jsonObj.put("spInf62", spInf62);
		jsonObj.put("spInf63", spInf63);
		jsonObj.put("spInf64", spInf64);
		jsonObj.put("spInf65", spInf65);
		jsonObj.put("spInf66", spInf66);

		jsonObj.put("spInfMP", spInfMinistryParts);
		jsonObj.put("spInfCP", spInfChristiansParts);

		return jsonObj;
	}

	public static JSONObject GET_ALL_CIRCUITOVERSEER_WEEKS(WeekOverseer weekStart, WeekOverseer weekEnd) {
		JSONObject jsonObj = create(Integer.valueOf(28));
		jsonObj.put("keyStart", weekStart.getKey());
		jsonObj.put("keyEnd", weekEnd.getKey());
		return jsonObj;
	}

	public static JSONObject INSERT_CIRCUITOVERSEER_WEEK(String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11,
			String spInf12, String spInf13, String spInf14, String spInf15, String spInf16, String spInf17,
			String spInf18, String spInf19, String spInf20, String spInf21, String spInf22) {

		JSONObject jsonObj = create(Integer.valueOf(29));
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

		return jsonObj;
	}

	public static JSONObject UPDATE_OVERSEER_WEEK(String spWeekOvID, String spInf1, String spInf2, String spInf3,
			String spInf4, String spInf5, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10,
			String spInf11, String spInf12, String spInf13, String spInf14, String spInf15, String spInf16,
			String spInf17, String spInf18, String spInf19, String spInf20, String spInf21, String spInf22) {

		JSONObject jsonObj = create(Integer.valueOf(30));

		jsonObj.put("spWeekOvID", spWeekOvID);
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

		return jsonObj;
	}

	public static JSONObject GET_ALL_ACTIVITIES(String password, String weekcode) {

		JSONObject jsonObj = create(Integer.valueOf(31));

		jsonObj.put("password", password);
		jsonObj.put("weekcode", weekcode);

		return jsonObj;
	}

	public static JSONObject UPDATE_MEMBER_NATURAL_DISASTER(String spMemberID, String spInf40, String spInf41) {

		JSONObject jsonObj = create(Integer.valueOf(32));

		jsonObj.put("spMemberID", spMemberID);
		jsonObj.put("spInf40", spInf40);
		jsonObj.put("spInf41", spInf41);

		return jsonObj;
	}

	public static JSONObject UPDATE_FAMILY_NATURAL_DISASTER(String spFamID, String spInf1, String spInf2, String spInf3,
			String spInf4, String spInf5, String spInf7) {

		JSONObject jsonObj = create(Integer.valueOf(33));

		jsonObj.put("spFamID", spFamID);
		jsonObj.put("spInf1", spInf1);
		jsonObj.put("spInf2", spInf2);
		jsonObj.put("spInf3", spInf3);
		jsonObj.put("spInf4", spInf4);
		jsonObj.put("spInf5", spInf5);
		jsonObj.put("spInf7", spInf7);

		return jsonObj;
	}

	public static JSONObject GET_LAST_CIRCUITOVERSEER_WEEKS() {

		JSONObject jsonObj = create(Integer.valueOf(34));

		return jsonObj;
	}

	public static JSONObject UPDATE_PUBLIC_MEETING(String spWeekID, String spInf30, String spInf31, String spInf32,
			String spInf33, String spInf34, String spInf62, int spInf65, int spInf66) {

		JSONObject jsonObj = create(Integer.valueOf(35));

		jsonObj.put("spWeekID", spWeekID);
		jsonObj.put("spInf30", spInf30);
		jsonObj.put("spInf31", spInf31);
		jsonObj.put("spInf32", spInf32);
		jsonObj.put("spInf33", spInf33);
		jsonObj.put("spInf34", spInf34);
		jsonObj.put("spInf62", spInf62);
		jsonObj.put("spInf65", spInf65);
		jsonObj.put("spInf66", spInf66);

		return jsonObj;
	}

	public static JSONObject CHECK_CONNECTION() {

		JSONObject jsonObj = create(Integer.valueOf(36));

		return jsonObj;
	}

	public static JSONObject CLEAN_DATABASE() {

		JSONObject jsonObj = create(Integer.valueOf(37));

		return jsonObj;
	}

	public static JSONObject DATE_AND_TIME_INSERT(DateAndTime dateAndTime) {

		JSONObject jsonObj = create(Integer.valueOf(38));

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dateString = pattern.format(dateAndTime.getDate().get());

		jsonObj.put("startDate", dateString);
		jsonObj.put("day1", dateAndTime.getDay1().get());
		jsonObj.put("hour1", dateAndTime.getHour1().get());
		jsonObj.put("minute1", dateAndTime.getMinute1().get());
		jsonObj.put("day2", dateAndTime.getDay2().get());
		jsonObj.put("hour2", dateAndTime.getHour2().get());
		jsonObj.put("minute2", dateAndTime.getMinute2().get());

		return jsonObj;
	}

	public static JSONObject DATE_AND_TIME_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(39));
		return jsonObj;
	}

	public static JSONObject DATE_AND_TIME_DELETE(DateAndTime dateAndTime) {

		JSONObject jsonObj = create(Integer.valueOf(40));

		jsonObj.put("id", dateAndTime.getId().get());

		return jsonObj;
	}

	public static JSONObject PLACE_INSERT(Place place) {

		JSONObject jsonObj = create(Integer.valueOf(41));

		jsonObj.put("typePlace", place.getType().get().getId());
		jsonObj.put("descr", place.getDescr().get());
		jsonObj.put("street", place.getStreet().get());
		jsonObj.put("num", place.getNum().get());
		jsonObj.put("postCode", place.getPostCode().get());
		jsonObj.put("city", place.getCity().get());
		jsonObj.put("county", place.getCounty().get());
		jsonObj.put("country", place.getCountry().get());
		jsonObj.put("coord", place.getCoord().get());

		int def = place.getDef().get() ? 1 : 0;
		jsonObj.put("def", def);

		return jsonObj;
	}

	public static JSONObject PLACES_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(42));
		return jsonObj;
	}

	public static JSONObject PLACE_DELETE(Place place) {

		JSONObject jsonObj = create(Integer.valueOf(43));

		jsonObj.put("id", place.getId().get());

		return jsonObj;
	}

	public static JSONObject USER_SAVE(int userID, int spInf1, int spInf2, int spInf3, int spInf4, int spInf5,
			int spInf6, int spInf7, int spInf8, int spInf9, int spInf10, int spInf11, int spInf12, int spInf13,
			int spInf14, int spInf15, int spInf16, int spInf17, int spInf18, int spInf19, int spInf20, int spInf21,
			int spInf22, int spInf23, int spInf24, int spInf25, int spInf26, int spInf27, int spInf28, int spInf29,
			int spInf30) {

		JSONObject jsonObj = create(Integer.valueOf(44));

		jsonObj.put("id", userID);
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

		return jsonObj;
	}

	public static JSONObject GENERAL_INFO_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(25));
		return jsonObj;
	}

	public static JSONObject GENERAL_INFO_UPDATE(String congregationNameEncrypted, String congregationNumberEncrypted) {

		JSONObject jsonObj = create(Integer.valueOf(24));

		JSONArray jsonArray = new JSONArray();
		jsonArray.put(createJSONKeyValue("inf1", congregationNameEncrypted));
		jsonArray.put(createJSONKeyValue("inf2", congregationNumberEncrypted));

		jsonObj.put("infos", jsonArray);

		return jsonObj;
	}

	public static JSONObject CONFIG_UPDATE(String placesPatternEncrypted, String publicTalkMinEncrypted,
			String watchtowerMinEncrypted, String overseerTalk1MinEncrypted, String overseerTalk2MinEncrypted,
			String overseerTalk3MinEncrypted, String overseerVisitCounterEncrypted, String memorialTalkMinEncrypted,
			String audio1, String audio2, String audio3, String usciere1, String usciere2, String usciere3,
			String usciere1equals, String usciere2equals, String usciere3equals, String songsMinEncrypted,
			String songsLoad) {

		JSONObject jsonObj = create(Integer.valueOf(45));

		JSONArray jsonArray = new JSONArray();
		jsonArray.put(createJSONKeyValue("inf1", placesPatternEncrypted));
		jsonArray.put(createJSONKeyValue("inf2", publicTalkMinEncrypted));
		jsonArray.put(createJSONKeyValue("inf3", watchtowerMinEncrypted));
		jsonArray.put(createJSONKeyValue("inf4", overseerTalk1MinEncrypted));
		jsonArray.put(createJSONKeyValue("inf5", overseerTalk2MinEncrypted));
		jsonArray.put(createJSONKeyValue("inf6", overseerTalk3MinEncrypted));
		jsonArray.put(createJSONKeyValue("inf7", overseerVisitCounterEncrypted));
		jsonArray.put(createJSONKeyValue("inf8", memorialTalkMinEncrypted));

		jsonArray.put(createJSONKeyValue("inf9", audio1));
		jsonArray.put(createJSONKeyValue("inf10", audio2));
		jsonArray.put(createJSONKeyValue("inf11", audio3));

		jsonArray.put(createJSONKeyValue("inf12", usciere1));
		jsonArray.put(createJSONKeyValue("inf13", usciere2));
		jsonArray.put(createJSONKeyValue("inf14", usciere3));

		jsonArray.put(createJSONKeyValue("inf15", usciere1equals));
		jsonArray.put(createJSONKeyValue("inf16", usciere2equals));
		jsonArray.put(createJSONKeyValue("inf17", usciere3equals));

		jsonArray.put(createJSONKeyValue("inf18", songsMinEncrypted));
		jsonArray.put(createJSONKeyValue("inf19", songsLoad));

		jsonObj.put("infos", jsonArray);

		return jsonObj;
	}

	public static JSONObject CONFIG_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(46));
		return jsonObj;
	}

	public static JSONObject CONVENTION_INSERT(WeekConvention convention) {

		JSONObject jsonObj = create(Integer.valueOf(47));

		jsonObj.put("spInf1", convention.getSpInf1());
		jsonObj.put("spInf2", convention.getSpInf2());
		jsonObj.put("spInf3", convention.getSpInf3());
		jsonObj.put("spInf4", convention.getSpInf4());
		jsonObj.put("spInf5", convention.getSpInf5());
		jsonObj.put("spInf6", convention.getSpInf6());
		jsonObj.put("spInf7", convention.getSpInf7());
		jsonObj.put("spInf8", convention.getSpInf8());
		jsonObj.put("spInf9", convention.getSpInf9());
		jsonObj.put("spInf10", convention.getSpInf10());
		jsonObj.put("spInf11", convention.getSpInf11());
		jsonObj.put("spInf12", convention.getSpInf12());
		jsonObj.put("spInf13", convention.getSpInf13());
		jsonObj.put("spInf14", convention.getSpInf14());
		jsonObj.put("spInf15", convention.getSpInf15());
		jsonObj.put("spInf16", convention.getSpInf16());
		jsonObj.put("spInf17", convention.getSpInf17());
		jsonObj.put("spInf18", convention.getSpInf18());
		jsonObj.put("spInf19", convention.getSpInf19());
		jsonObj.put("spInf20", convention.getSpInf20());
		jsonObj.put("spInf21", convention.getSpInf21());
		jsonObj.put("spInf22", convention.getSpInf22());
		jsonObj.put("spInf23", convention.getSpInf23());
		jsonObj.put("spInf24", convention.getSpInf24());
		jsonObj.put("spInf25", convention.getSpInf25());
		jsonObj.put("spInf26", convention.getSpInf26());
		jsonObj.put("spInf27", convention.getSpInf27());
		jsonObj.put("spInf28", convention.getSpInf28());
		jsonObj.put("spInf29", convention.getSpInf29());
		jsonObj.put("spInf30", convention.getSpInf30());
		jsonObj.put("spInf31", convention.getSpInf31());
		jsonObj.put("spInf32", convention.getSpInf32());

		return jsonObj;
	}

	public static JSONObject CONVENTION_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(48));
		return jsonObj;
	}

	public static JSONObject CONVENTION_UPDATE(WeekConvention convention) {

		JSONObject jsonObj = create(Integer.valueOf(49));

		jsonObj.put("spConvenID", convention.getConvenID());
		jsonObj.put("spInf1", convention.getSpInf1());
		jsonObj.put("spInf2", convention.getSpInf2());
		jsonObj.put("spInf3", convention.getSpInf3());
		jsonObj.put("spInf4", convention.getSpInf4());
		jsonObj.put("spInf5", convention.getSpInf5());
		jsonObj.put("spInf6", convention.getSpInf6());
		jsonObj.put("spInf7", convention.getSpInf7());
		jsonObj.put("spInf8", convention.getSpInf8());
		jsonObj.put("spInf9", convention.getSpInf9());
		jsonObj.put("spInf10", convention.getSpInf10());
		jsonObj.put("spInf11", convention.getSpInf11());
		jsonObj.put("spInf12", convention.getSpInf12());
		jsonObj.put("spInf13", convention.getSpInf13());
		jsonObj.put("spInf14", convention.getSpInf14());
		jsonObj.put("spInf15", convention.getSpInf15());
		jsonObj.put("spInf16", convention.getSpInf16());
		jsonObj.put("spInf17", convention.getSpInf17());
		jsonObj.put("spInf18", convention.getSpInf18());
		jsonObj.put("spInf19", convention.getSpInf19());
		jsonObj.put("spInf20", convention.getSpInf20());
		jsonObj.put("spInf21", convention.getSpInf21());
		jsonObj.put("spInf22", convention.getSpInf22());
		jsonObj.put("spInf23", convention.getSpInf23());
		jsonObj.put("spInf24", convention.getSpInf24());
		jsonObj.put("spInf25", convention.getSpInf25());
		jsonObj.put("spInf26", convention.getSpInf26());
		jsonObj.put("spInf27", convention.getSpInf27());
		jsonObj.put("spInf28", convention.getSpInf28());
		jsonObj.put("spInf29", convention.getSpInf29());
		jsonObj.put("spInf30", convention.getSpInf30());
		jsonObj.put("spInf31", convention.getSpInf31());
		jsonObj.put("spInf32", convention.getSpInf32());

		return jsonObj;
	}

	public static JSONObject CONVENTION_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(50));

		jsonObj.put("spConvenID", id);

		return jsonObj;
	}

	public static JSONObject MEMORIAL_INSERT(WeekMemorial memorial) {

		JSONObject jsonObj = create(Integer.valueOf(51));

		jsonObj.put("spInf1", memorial.getSpInf1());
		jsonObj.put("spInf2", memorial.getSpInf2());
		jsonObj.put("spInf3", memorial.getSpInf3());
		jsonObj.put("spInf4", memorial.getSpInf4());
		jsonObj.put("spInf5", memorial.getSpInf5());
		jsonObj.put("spInf6", memorial.getSpInf6());
		jsonObj.put("spInf7", memorial.getSpInf7());
		jsonObj.put("spInf8", memorial.getSpInf8());
		jsonObj.put("spInf9", memorial.getSpInf9());
		jsonObj.put("spInf10", memorial.getSpInf10());
		jsonObj.put("spInf11", memorial.getSpInf11());
		jsonObj.put("spInf12", memorial.getSpInf12());
		jsonObj.put("spInf13", memorial.getSpInf13());
		jsonObj.put("spInf14", memorial.getSpInf14());
		jsonObj.put("spInf15", memorial.getSpInf15());
		jsonObj.put("spInf16", memorial.getSpInf16());
		jsonObj.put("spInf17", memorial.getSpInf17());
		jsonObj.put("spInf18", memorial.getSpInf18());
		jsonObj.put("spInf19", memorial.getSpInf19());
		jsonObj.put("spInf20", memorial.getSpInf20());
		jsonObj.put("spInf21", memorial.getSpInf21());
		jsonObj.put("spInf22", memorial.getSpInf22());
		jsonObj.put("spInf23", memorial.getSpInf23());
		jsonObj.put("spInf24", memorial.getSpInf24());
		jsonObj.put("spInf25", memorial.getSpInf25());
		jsonObj.put("spInf26", memorial.getSpInf26());
		jsonObj.put("spInf27", memorial.getSpInf27());
		jsonObj.put("spInf28", memorial.getSpInf28());
		jsonObj.put("spInf29", memorial.getSpInf29());
		jsonObj.put("spInf30", memorial.getSpInf30());
		jsonObj.put("spInf31", memorial.getSpInf31());
		jsonObj.put("spInf32", memorial.getSpInf32());
		jsonObj.put("spInf33", memorial.getSpInf33());
		jsonObj.put("spInf34", memorial.getSpInf34());
		jsonObj.put("spInf35", memorial.getSpInf35());
		jsonObj.put("spInf36", memorial.getSpInf36());

		jsonObj.put("spInf37", memorial.getSpInf37());
		jsonObj.put("spInf38", memorial.getSpInf38());

		return jsonObj;
	}

	public static JSONObject MEMORIAL_UPDATE(WeekMemorial memorial) {

		JSONObject jsonObj = create(Integer.valueOf(52));

		jsonObj.put("spMemorialID", memorial.getMemorialID());
		jsonObj.put("spInf1", memorial.getSpInf1());
		jsonObj.put("spInf2", memorial.getSpInf2());
		jsonObj.put("spInf3", memorial.getSpInf3());
		jsonObj.put("spInf4", memorial.getSpInf4());
		jsonObj.put("spInf5", memorial.getSpInf5());
		jsonObj.put("spInf6", memorial.getSpInf6());
		jsonObj.put("spInf7", memorial.getSpInf7());
		jsonObj.put("spInf8", memorial.getSpInf8());
		jsonObj.put("spInf9", memorial.getSpInf9());
		jsonObj.put("spInf10", memorial.getSpInf10());
		jsonObj.put("spInf11", memorial.getSpInf11());
		jsonObj.put("spInf12", memorial.getSpInf12());
		jsonObj.put("spInf13", memorial.getSpInf13());
		jsonObj.put("spInf14", memorial.getSpInf14());
		jsonObj.put("spInf15", memorial.getSpInf15());
		jsonObj.put("spInf16", memorial.getSpInf16());
		jsonObj.put("spInf17", memorial.getSpInf17());
		jsonObj.put("spInf18", memorial.getSpInf18());
		jsonObj.put("spInf19", memorial.getSpInf19());
		jsonObj.put("spInf20", memorial.getSpInf20());
		jsonObj.put("spInf21", memorial.getSpInf21());
		jsonObj.put("spInf22", memorial.getSpInf22());
		jsonObj.put("spInf23", memorial.getSpInf23());
		jsonObj.put("spInf24", memorial.getSpInf24());
		jsonObj.put("spInf25", memorial.getSpInf25());
		jsonObj.put("spInf26", memorial.getSpInf26());
		jsonObj.put("spInf27", memorial.getSpInf27());
		jsonObj.put("spInf28", memorial.getSpInf28());
		jsonObj.put("spInf29", memorial.getSpInf29());
		jsonObj.put("spInf30", memorial.getSpInf30());
		jsonObj.put("spInf31", memorial.getSpInf31());
		jsonObj.put("spInf32", memorial.getSpInf32());
		jsonObj.put("spInf33", memorial.getSpInf33());
		jsonObj.put("spInf34", memorial.getSpInf34());
		jsonObj.put("spInf35", memorial.getSpInf35());
		jsonObj.put("spInf36", memorial.getSpInf36());

		jsonObj.put("spInf37", memorial.getSpInf37());
		jsonObj.put("spInf38", memorial.getSpInf38());

		return jsonObj;
	}

	public static JSONObject MEMORIAL_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(53));
		return jsonObj;
	}

	public static JSONObject MEMORIAL_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(54));

		jsonObj.put("spMemorialID", id);

		return jsonObj;
	}

	public static JSONObject OVERSEER_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(55));

		jsonObj.put("spWeekOvID", id);

		return jsonObj;
	}

	public static JSONObject AUDIO_INSERT(WeekAudio w) {

		JSONObject jsonObj = create(Integer.valueOf(56));

		jsonObj.put("spInf1", w.getSpInf1());
		jsonObj.put("spInf2", w.getSpInf2());
		jsonObj.put("spInf3", w.getSpInf3());
		jsonObj.put("spInf4", w.getSpInf4());
		jsonObj.put("spInf5", w.getSpInf5());
		jsonObj.put("spInf6", w.getSpInf6());
		jsonObj.put("spInf7", w.getSpInf7());
		jsonObj.put("spInf8", w.getSpInf8());
		jsonObj.put("spInf9", w.getSpInf9());
		jsonObj.put("spInf10", w.getSpInf10());
		jsonObj.put("spInf11", w.getSpInf11());
		jsonObj.put("spInf12", w.getSpInf12());
		jsonObj.put("spInf13", w.getSpInf13());

		return jsonObj;
	}

	public static JSONObject AUDIO_UPDATE(WeekAudio w) {

		JSONObject jsonObj = create(Integer.valueOf(57));

		jsonObj.put("spAudioID", w.getAudioID());
		jsonObj.put("spInf1", w.getSpInf1());
		jsonObj.put("spInf2", w.getSpInf2());
		jsonObj.put("spInf3", w.getSpInf3());
		jsonObj.put("spInf4", w.getSpInf4());
		jsonObj.put("spInf5", w.getSpInf5());
		jsonObj.put("spInf6", w.getSpInf6());
		jsonObj.put("spInf7", w.getSpInf7());
		jsonObj.put("spInf8", w.getSpInf8());
		jsonObj.put("spInf9", w.getSpInf9());
		jsonObj.put("spInf10", w.getSpInf10());
		jsonObj.put("spInf11", w.getSpInf11());
		jsonObj.put("spInf12", w.getSpInf12());
		jsonObj.put("spInf13", w.getSpInf13());

		return jsonObj;
	}

	public static JSONObject AUDIO_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(58));
		return jsonObj;
	}

	public static JSONObject AUDIO_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(59));

		jsonObj.put("spAudioID", id);

		return jsonObj;
	}

	public static JSONObject USCIERE_INSERT(WeekUsciere w) {

		JSONObject jsonObj = create(Integer.valueOf(60));

		jsonObj.put("spInf1", w.getSpInf1());
		jsonObj.put("spInf2", w.getSpInf2());
		jsonObj.put("spInf3", w.getSpInf3());
		jsonObj.put("spInf4", w.getSpInf4());
		jsonObj.put("spInf5", w.getSpInf5());
		jsonObj.put("spInf6", w.getSpInf6());
		jsonObj.put("spInf7", w.getSpInf7());
		jsonObj.put("spInf8", w.getSpInf8());
		jsonObj.put("spInf9", w.getSpInf9());
		jsonObj.put("spInf10", w.getSpInf10());
		jsonObj.put("spInf11", w.getSpInf11());
		jsonObj.put("spInf12", w.getSpInf12());
		jsonObj.put("spInf13", w.getSpInf13());
		jsonObj.put("spInf14", w.getSpInf14());
		jsonObj.put("spInf15", w.getSpInf15());
		jsonObj.put("spInf16", w.getSpInf16());
		jsonObj.put("spInf17", w.getSpInf17());
		jsonObj.put("spInf18", w.getSpInf18());
		jsonObj.put("spInf19", w.getSpInf19());

		return jsonObj;
	}

	public static JSONObject USCIERE_UPDATE(WeekUsciere w) {

		JSONObject jsonObj = create(Integer.valueOf(61));

		jsonObj.put("spUscID", w.getUscID());
		jsonObj.put("spInf1", w.getSpInf1());
		jsonObj.put("spInf2", w.getSpInf2());
		jsonObj.put("spInf3", w.getSpInf3());
		jsonObj.put("spInf4", w.getSpInf4());
		jsonObj.put("spInf5", w.getSpInf5());
		jsonObj.put("spInf6", w.getSpInf6());
		jsonObj.put("spInf7", w.getSpInf7());
		jsonObj.put("spInf8", w.getSpInf8());
		jsonObj.put("spInf9", w.getSpInf9());
		jsonObj.put("spInf10", w.getSpInf10());
		jsonObj.put("spInf11", w.getSpInf11());
		jsonObj.put("spInf12", w.getSpInf12());
		jsonObj.put("spInf13", w.getSpInf13());
		jsonObj.put("spInf14", w.getSpInf14());
		jsonObj.put("spInf15", w.getSpInf15());
		jsonObj.put("spInf16", w.getSpInf16());
		jsonObj.put("spInf17", w.getSpInf17());
		jsonObj.put("spInf18", w.getSpInf18());
		jsonObj.put("spInf19", w.getSpInf19());

		return jsonObj;
	}

	public static JSONObject USCIERE_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(62));
		return jsonObj;
	}

	public static JSONObject USCIERE_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(63));

		jsonObj.put("spUscID", id);

		return jsonObj;
	}

	public static JSONObject SONGS_UPDATE(SecretKey secretKey, ObservableList<Song> songList) {

		JSONObject jsonObj = create(Integer.valueOf(64));

		JSONArray jsonArray = new JSONArray();

		for (Song song : songList) {

			int number = song.getNumber().intValue();
			String title = song.getTitle();

			String numberEncrypted = Crypt.encrypt(String.valueOf(number), secretKey);
			String titleEncrypted = Crypt.encrypt(title, secretKey);

			jsonArray.put(createJSONKeyValue(numberEncrypted, titleEncrypted));
		}

		jsonObj.put("songs", jsonArray);

		return jsonObj;
	}

	public static JSONObject SONGS_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(65));
		return jsonObj;
	}

	public static JSONObject PDFREPLACE_INSERT(PDFReplace pdfReplace) {

		JSONObject jsonObj = create(Integer.valueOf(66));

		jsonObj.put("spInf1", pdfReplace.getSpInf1());
		jsonObj.put("spInf2", pdfReplace.getSpInf2());
		jsonObj.put("spInf3", pdfReplace.getSpInf3());

		return jsonObj;
	}

	public static JSONObject PDFREPLACE_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(67));
		return jsonObj;
	}

	public static JSONObject PDFREPLACE_REMOVE(PDFReplace pdfReplace) {

		JSONObject jsonObj = create(Integer.valueOf(68));

		jsonObj.put("spInfID", pdfReplace.getSpInfID());

		return jsonObj;
	}

	public static JSONObject PDFDEST_INSERT(PDFDest pdfDest) {

		JSONObject jsonObj = create(Integer.valueOf(69));

		jsonObj.put("spInf1", pdfDest.getSpInf1());
		jsonObj.put("spInf2", pdfDest.getSpInf2());

		return jsonObj;
	}

	public static JSONObject PDFDEST_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(70));
		return jsonObj;
	}

	public static JSONObject PDFDEST_REMOVE(PDFDest pdfDest) {

		JSONObject jsonObj = create(Integer.valueOf(71));

		jsonObj.put("spInfID", pdfDest.getSpInfID());

		return jsonObj;
	}

	public static JSONObject PDF_IMPORT(SecretKey secretKey, PostImportDoc doc) {

		JSONObject jsonObj = create(Integer.valueOf(72));

		JSONArray jsonArray = new JSONArray();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate docDate = doc.getDocDate();
		String docDateText = dtf.format(docDate);
		String docName = Crypt.encrypt(doc.getDocName(), secretKey);
		String docTitle = Crypt.encrypt(doc.getDocTitle(), secretKey);

		for (PostImportNews news : doc.getDocNewst()) {

			String destinatario = Crypt.encrypt(news.getDest(), secretKey);
			String title = Crypt.encrypt(news.getTitle(), secretKey);
			String newsText = Crypt.encrypt(news.getText(), secretKey);

			JSONObject jsonObject = new JSONObject();

			jsonObject.put("spInf1", docDateText);
			jsonObject.put("spInf2", destinatario);
			jsonObject.put("spInf3", title);
			jsonObject.put("spInf4", newsText);
			jsonObject.put("spInf5", docName);
			jsonObject.put("spInf6", docTitle);
			jsonObject.put("spInf7", "0");
			jsonObject.put("spInf8", "0");

			jsonArray.put(jsonObject);
		}

		jsonObj.put("news", jsonArray);

		return jsonObj;
	}

	public static JSONObject POST_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(73));
		return jsonObj;
	}

	public static JSONObject POST_INFOTABLE_UPDATE(PostNews postNews, String spInf7) {

		JSONObject jsonObj = create(Integer.valueOf(74));

		jsonObj.put("spInfID", postNews.getSpInfID());
		jsonObj.put("spInf7", spInf7);

		return jsonObj;
	}

	public static JSONObject POST_DELETE(PostNews postNews) {

		JSONObject jsonObj = create(Integer.valueOf(75));

		jsonObj.put("spInfID", postNews.getSpInfID());

		return jsonObj;
	}

	public static JSONObject INFOTABLE_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(76));
		return jsonObj;
	}

	public static JSONObject OVERSEER_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(77));
		return jsonObj;
	}

	public static JSONObject MEETING_LOAD() {
		JSONObject jsonObj = create(Integer.valueOf(78));
		return jsonObj;
	}

	public static JSONObject TERRITORY_INSERT(TerritoryObj obj) {

		JSONObject jsonObj = create(Integer.valueOf(79));

		jsonObj.put("spInf1", obj.getSpInf1());
		jsonObj.put("spInf2", obj.getSpInf2());
		jsonObj.put("spInf3", obj.getSpInf3());
		jsonObj.put("spInf4", obj.getSpInf4());
		jsonObj.put("spInf5", obj.getSpInf5());
		jsonObj.put("spInf6", obj.getSpInf6());
		jsonObj.put("spInf7", obj.getSpInf7());
		jsonObj.put("spInf8", obj.getSpInf8());
		jsonObj.put("spInf9", obj.getSpInf9());
		jsonObj.put("spInf10", obj.getSpInf10());
		jsonObj.put("spInf11", obj.getSpInf11());
		jsonObj.put("spInf12", obj.getSpInf12());
		jsonObj.put("spInf13", obj.getSpInf13());
		jsonObj.put("spInf14", obj.getSpInf14());
		jsonObj.put("spInf15", obj.getSpInf15());
		jsonObj.put("spInf16", obj.getSpInf16());
		jsonObj.put("spInf17", obj.getSpInf17());
		jsonObj.put("spInf18", obj.getSpInf18());
		jsonObj.put("spInf19", obj.getSpInf19());
		jsonObj.put("spInf20", obj.getSpInf20());
		jsonObj.put("spInf21", obj.getSpInf21());
		jsonObj.put("spInf22", obj.getSpInf22());
		jsonObj.put("spInf23", obj.getSpInf23());
		jsonObj.put("spInf24", obj.getSpInf24());
		jsonObj.put("spInf25", obj.getSpInf25());
		jsonObj.put("spInf26", obj.getSpInf26());
		jsonObj.put("spInf27", obj.getSpInf27());
		jsonObj.put("spInf28", obj.getSpInf28());
		jsonObj.put("spInf29", obj.getSpInf29());
		jsonObj.put("spInf30", obj.getSpInf30());
		jsonObj.put("spInf31", obj.getSpInf31());
		jsonObj.put("spInf32", obj.getSpInf32());
		jsonObj.put("spInf33", obj.getSpInf33());
		jsonObj.put("spInf34", obj.getSpInf34());
		jsonObj.put("spInf35", obj.getSpInf35());
		jsonObj.put("spInf36", obj.getSpInf36());
		jsonObj.put("spInf37", obj.getSpInf37());
		jsonObj.put("spInf38", obj.getSpInf38());
		jsonObj.put("spInf39", obj.getSpInf39());
		jsonObj.put("spInf40", obj.getSpInf40());

		jsonObj.put("spInf41", obj.getSpInf41());
		jsonObj.put("spInf42", obj.getSpInf42());
		jsonObj.put("spInf43", obj.getSpInf43());
		jsonObj.put("spInf44", obj.getSpInf44());
		jsonObj.put("spInf45", obj.getSpInf45());
		jsonObj.put("spInf46", obj.getSpInf46());
		jsonObj.put("spInf47", obj.getSpInf47());
		jsonObj.put("spInf48", obj.getSpInf48());

		return jsonObj;
	}

	public static JSONObject TERRITORY_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(80));
		return jsonObj;
	}

	public static JSONObject TERRITORY_UPDATE(TerritoryObj obj) {

		JSONObject jsonObj = create(Integer.valueOf(81));

		jsonObj.put("id", obj.getSpTerritoryID());
		jsonObj.put("spInf1", obj.getSpInf1());
		jsonObj.put("spInf2", obj.getSpInf2());
		jsonObj.put("spInf3", obj.getSpInf3());
		jsonObj.put("spInf4", obj.getSpInf4());
		jsonObj.put("spInf5", obj.getSpInf5());
		jsonObj.put("spInf6", obj.getSpInf6());
		jsonObj.put("spInf7", obj.getSpInf7());
		jsonObj.put("spInf8", obj.getSpInf8());
		jsonObj.put("spInf9", obj.getSpInf9());
		jsonObj.put("spInf10", obj.getSpInf10());
		jsonObj.put("spInf11", obj.getSpInf11());
		jsonObj.put("spInf12", obj.getSpInf12());
		jsonObj.put("spInf13", obj.getSpInf13());
		jsonObj.put("spInf14", obj.getSpInf14());
		jsonObj.put("spInf15", obj.getSpInf15());
		jsonObj.put("spInf16", obj.getSpInf16());
		jsonObj.put("spInf17", obj.getSpInf17());
		jsonObj.put("spInf18", obj.getSpInf18());
		jsonObj.put("spInf19", obj.getSpInf19());
		jsonObj.put("spInf20", obj.getSpInf20());
		jsonObj.put("spInf21", obj.getSpInf21());
		jsonObj.put("spInf22", obj.getSpInf22());
		jsonObj.put("spInf23", obj.getSpInf23());
		jsonObj.put("spInf24", obj.getSpInf24());
		jsonObj.put("spInf25", obj.getSpInf25());
		jsonObj.put("spInf26", obj.getSpInf26());
		jsonObj.put("spInf27", obj.getSpInf27());
		jsonObj.put("spInf28", obj.getSpInf28());
		jsonObj.put("spInf29", obj.getSpInf29());
		jsonObj.put("spInf30", obj.getSpInf30());
		jsonObj.put("spInf31", obj.getSpInf31());
		jsonObj.put("spInf32", obj.getSpInf32());
		jsonObj.put("spInf33", obj.getSpInf33());
		jsonObj.put("spInf34", obj.getSpInf34());
		jsonObj.put("spInf35", obj.getSpInf35());
		jsonObj.put("spInf36", obj.getSpInf36());
		jsonObj.put("spInf37", obj.getSpInf37());
		jsonObj.put("spInf38", obj.getSpInf38());
		jsonObj.put("spInf39", obj.getSpInf39());
		jsonObj.put("spInf40", obj.getSpInf40());

		jsonObj.put("spInf41", obj.getSpInf41());
		jsonObj.put("spInf42", obj.getSpInf42());
		jsonObj.put("spInf43", obj.getSpInf43());
		jsonObj.put("spInf44", obj.getSpInf44());
		jsonObj.put("spInf45", obj.getSpInf45());
		jsonObj.put("spInf46", obj.getSpInf46());
		jsonObj.put("spInf47", obj.getSpInf47());
		jsonObj.put("spInf48", obj.getSpInf48());

		return jsonObj;
	}

	public static JSONObject TERRITORY_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(82));

		jsonObj.put("id", id);

		return jsonObj;
	}

	public static JSONObject TERRITORY_REGISTRY_INSERT(TerritoryObj territoryObj, Member member, LocalDate assignDate,
			boolean territoryGroup, SecretKey secretKey) {

		JSONObject jsonObj = create(Integer.valueOf(83));

		jsonObj.put("spInf1", territoryObj.getSpTerritoryID());
		jsonObj.put("spInf2", member.getSpMemberID());
//		jsonObj.put("spInf3", Crypt.encrypt(assignDate.toString(), secretKey));
//		jsonObj.put("spInf4", Crypt.encrypt("", secretKey));
		jsonObj.put("spInf3", assignDate.toString());
		jsonObj.put("spInf4", "");
		jsonObj.put("spInf5", territoryGroup ? "1" : "0");

		return jsonObj;
	}

	public static JSONObject TERRITORY_REGISTRY_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(84));
		return jsonObj;
	}

	public static JSONObject TERRITORY_REGISTRY_INSERT_RETURN(TerritoryRegistryEntity territoriesEntity,
			LocalDate returnDate, SecretKey secretKey) {

		JSONObject jsonObj = create(Integer.valueOf(85));

		jsonObj.put("id", territoriesEntity.getID());
//		jsonObj.put("spInf4", Crypt.encrypt(returnDate.toString(), secretKey));
		jsonObj.put("spInf4", returnDate.toString());

		return jsonObj;
	}

	public static JSONObject TERRITORYENTITY_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(86));

		jsonObj.put("id", id);

		return jsonObj;
	}

	public static JSONObject TERRITORY_MAPS_INSERT(TerritoryMap obj) {

		JSONObject jsonObj = create(Integer.valueOf(87));

		jsonObj.put("spInf1", obj.getSpInf1());
		jsonObj.put("spInf2", obj.getSpInf2());
		jsonObj.put("spInf3", obj.getSpInf3());
		jsonObj.put("spInf4", obj.getSpInf4());
		jsonObj.put("spInf5", obj.getSpInf5());
		jsonObj.put("spInf6", obj.getSpInf6());
		jsonObj.put("spInf7", obj.getSpInf7());
		jsonObj.put("spInf8", obj.getSpInf8());
		jsonObj.put("spInf9", obj.getSpInf9());
		jsonObj.put("spInf10", obj.getSpInf10());
		jsonObj.put("spInf11", obj.getSpInf11());
		jsonObj.put("spInf12", obj.getSpInf12());
		jsonObj.put("spInf13", obj.getSpInf13());
		jsonObj.put("spInf14", obj.getSpInf14());
		jsonObj.put("spInf15", obj.getSpInf15());
		jsonObj.put("spInf16", obj.getSpInf16());
		jsonObj.put("spInf17", obj.getSpInf17());
		jsonObj.put("spInf18", obj.getSpInf18());
		jsonObj.put("spInf19", obj.getSpInf19());
		jsonObj.put("spInf20", obj.getSpInf20());
		jsonObj.put("spInf21", obj.getSpInf21());
		jsonObj.put("spInf22", obj.getSpInf22());
		jsonObj.put("spInf23", obj.getSpInf23());
		jsonObj.put("spInf24", obj.getSpInf24());
		jsonObj.put("spInf25", obj.getSpInf25());
		jsonObj.put("spInf26", obj.getSpInf26());
		jsonObj.put("spInf27", obj.getSpInf27());
		jsonObj.put("spInf28", obj.getSpInf28());
		jsonObj.put("spInf29", obj.getSpInf29());
		jsonObj.put("spInf30", obj.getSpInf30());

		return jsonObj;
	}

	public static JSONObject TERRITORY_MAPS_LOAD() {

		JSONObject jsonObj = create(Integer.valueOf(88));
		return jsonObj;
	}

	public static JSONObject TERRITORY_MAPS_UPDATE(TerritoryMap obj) {

		JSONObject jsonObj = create(Integer.valueOf(89));

		jsonObj.put("id", obj.getSpTerritoryID());
		jsonObj.put("spInf1", obj.getSpInf1());
		jsonObj.put("spInf2", obj.getSpInf2());
		jsonObj.put("spInf3", obj.getSpInf3());
		jsonObj.put("spInf4", obj.getSpInf4());
		jsonObj.put("spInf5", obj.getSpInf5());
		jsonObj.put("spInf6", obj.getSpInf6());
		jsonObj.put("spInf7", obj.getSpInf7());
		jsonObj.put("spInf8", obj.getSpInf8());
		jsonObj.put("spInf9", obj.getSpInf9());
		jsonObj.put("spInf10", obj.getSpInf10());
		jsonObj.put("spInf11", obj.getSpInf11());
		jsonObj.put("spInf12", obj.getSpInf12());
		jsonObj.put("spInf13", obj.getSpInf13());
		jsonObj.put("spInf14", obj.getSpInf14());
		jsonObj.put("spInf15", obj.getSpInf15());
		jsonObj.put("spInf16", obj.getSpInf16());
		jsonObj.put("spInf17", obj.getSpInf17());
		jsonObj.put("spInf18", obj.getSpInf18());
		jsonObj.put("spInf19", obj.getSpInf19());
		jsonObj.put("spInf20", obj.getSpInf20());
		jsonObj.put("spInf21", obj.getSpInf21());
		jsonObj.put("spInf22", obj.getSpInf22());
		jsonObj.put("spInf23", obj.getSpInf23());
		jsonObj.put("spInf24", obj.getSpInf24());
		jsonObj.put("spInf25", obj.getSpInf25());
		jsonObj.put("spInf26", obj.getSpInf26());
		jsonObj.put("spInf27", obj.getSpInf27());
		jsonObj.put("spInf28", obj.getSpInf28());
		jsonObj.put("spInf29", obj.getSpInf29());
		jsonObj.put("spInf30", obj.getSpInf30());

		return jsonObj;
	}

	public static JSONObject TERRITORY_MAPS_DELETE(int id) {

		JSONObject jsonObj = create(Integer.valueOf(90));

		jsonObj.put("id", id);

		return jsonObj;
	}

	private static JSONObject createJSONKeyValue(String key, String value) {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("key", key);
		jsonObject.put("value", value);

		return jsonObject;
	}

	public static JSONStatus getStatus(JSONObject jsonObject) {
		int status = -1;
		if (jsonObject != null) {
			try {
				status = jsonObject.getInt("status");
			} catch (JSONException e) {
			}
		}
		JSONStatus jsonStatus = JSONStatus.getFromId(Integer.valueOf(status));
		return (jsonStatus != null) ? jsonStatus : JSONStatus.DB_RESULT_EMPTY;
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
			System.out.println(jsonStatus.getText());
			break;
		}
		return status;
	}

	private static JSONObject create(Integer type) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("version", Meta.Application.VERSION);
		jsonObj.put("type", type.intValue());
		return jsonObj;
	}
}
