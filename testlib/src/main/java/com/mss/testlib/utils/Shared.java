package com.mss.testlib.utils;

import android.content.Context;

import com.mss.testlib.R;
import com.mss.testlib.utils.constants.WScodes;

public class Shared {
    public static String CheckError(Context context, String resultCode) {

        if (resultCode.equals(WScodes.BD_KO_USSD)) {
            return context.getResources().getString(R.string.error_servenu);
        } else if (resultCode.equals(WScodes.Erreur_Pwd_Incorrect)) {
            return context.getResources().getString(R.string.error_5023);
        } else if (resultCode.equals(WScodes.Erreur_Statut_MSISDN)) {
            return context.getResources().getString(R.string.erreur_111);
        } else if (resultCode.equals(WScodes.MSISDN_inexistant) || resultCode.equals(WScodes.CheckMsisdnONP_KO) || resultCode.equals(WScodes.Client_inexistant)) {
            return context.getResources().getString(R.string.erreur_111);
        } else if (resultCode.equals(WScodes.MSISDN_Bloqué)) {
            return context.getResources().getString(R.string.error_423_blocked);
        } else if (resultCode.equals(WScodes.MSISDN_Désinscrit)) {
            return context.getResources().getString(R.string.erreur_140);
        } else if (resultCode.equals(WScodes.MSISDN_Preinscrit)) {
            return context.getResources().getString(R.string.erreur_139);
        } else if (resultCode.equals(WScodes.Msisdn_Non_Inscrit)) {
            return context.getResources().getString(R.string.erreur_169);
        } else if (resultCode.equals(WScodes.Erreur_Solde)) {
            return context.getResources().getString(R.string.solde_insufisant);
        } else if (resultCode.equals(WScodes.Solde_Insuffisant)) {
            return context.getResources().getString(R.string.solde_insufisant);
        } else if (resultCode.equals(WScodes.MSISDN_Incorrect)) {
            return context.getResources().getString(R.string.erreur_013);
        } else if (resultCode.equals(WScodes.Erreur_Format_IDM)) {
            return context.getResources().getString(R.string.code_comercant_error);
        } else if (resultCode.equals(WScodes.Service_Incorrect)) {
            return context.getResources().getString(R.string.error_servenu);
        } else if (resultCode.equals(WScodes.Erreur_Format_Pwd)) {
            return context.getResources().getString(R.string.error_451);
        } else if (resultCode.equals(WScodes.ERR_format_montant)) {
            return context.getResources().getString(R.string.montant_invalide);
        } else if (resultCode.equals(WScodes.User_inexistant)) {
            return context.getResources().getString(R.string.erreur_169);
        }else if (resultCode.equals(WScodes.MSISDN_Déja_Inscrit_avec_Autre_Service)) {
            return context.getResources().getString(R.string.Msisdn_Deja_Inscrit_autr_opr);
        } else if (resultCode.equals(WScodes.Caisse_inexistante)) {
            return context.getResources().getString(R.string.erreur_caisse);
        } else if (resultCode.equals(WScodes.Merchant_inexistant)) {
            return context.getResources().getString(R.string.comm_inexis);
        } else if (resultCode.equals(WScodes.Transaction_inexistante)) {
            return context.getResources().getString(R.string.trans_inexis);
        } else if (resultCode.equals(WScodes.Transaction_Refusee)) {
            return context.getResources().getString(R.string.trans_refusee);
        } else if (resultCode.equals(WScodes.ERR_transaction_Mobicash_KO) || resultCode.equals(WScodes.Erreur_Credit)
                || resultCode.equals(WScodes.Erreur_Debit) || resultCode.equals(WScodes.Erreur_Transaction_annulee)) {
            return context.getResources().getString(R.string.trans_non_effec);
        } else if (resultCode.equals(WScodes.Historique_vide)) {
            return context.getResources().getString(R.string.hist_vide);
        } else if (resultCode.equals(WScodes.User_non_Active)) {
            return context.getResources().getString(R.string.user_non_active);
        } else if (resultCode.equals(WScodes.Format_ReferenceNumber)) {
            return context.getResources().getString(R.string.format_referencenumber);
        } else if (resultCode.equals(WScodes.RefeNum_Inexistant)) {
            return context.getResources().getString(R.string.refenum_inexistant);
        } else if (resultCode.equals(WScodes.Favori_Existant)) {
            return context.getResources().getString(R.string.favori_existant);
        } else if (resultCode.equals(WScodes.Pas_de_facture)) {
            return context.getResources().getString(R.string.pas_de_facture);
        } else if (resultCode.equals(WScodes.BD_STEG_KO)) {
            return context.getResources().getString(R.string.bd_steg_ko);
        } /*else if (resultCode.equals(WScodes.Categorie_abonnement_KO)) {
            return )(context.getResources().getString(R.string.categorie_abonnement_ko);

        }*/ else if (resultCode.equals(WScodes.Paiement_en_cours)) {
            return context.getResources().getString(R.string.paiement_en_cours);
        } else if (resultCode.equals(WScodes.Facture_Deja_Payee)) {
            return context.getResources().getString(R.string.facture_deja_payee);
        } else if (resultCode.equals(WScodes.Abonnement_Resilie)) {
            return context.getResources().getString(R.string.abonnement_resilie);
        } else if (resultCode.equals(WScodes.Id_Eleve_inexistant)) {
            return context.getResources().getString(R.string.ID_ELEVE_INEXISTENT);
        } else if (resultCode.equals(WScodes.Eleve_Inscrit_par_web)) {
            return context.getResources().getString(R.string.ELEVE_INSCRITt_PAR_WEB);
        } else if (resultCode.equals(WScodes.Eleve_Inscrit_par_phone)) {
            return context.getResources().getString(R.string.ELEVE_INSCRIT_PAR_PHONE);
        } else if (resultCode.equals(WScodes.Eleve_Inscrit)) {
            return context.getResources().getString(R.string.ELEVE_DEJA_INSCRIT);
        } else if (resultCode.equals(WScodes.CB_DEJA_UTILISER)) {
            return context.getResources().getString(R.string.CB_DEJA_UTILISER);
        }else if (resultCode.equals(WScodes.ERR_ACCOUTN_CASUAL_BET_NUM) || resultCode.equals(WScodes.COMPTE_INEXISTANT) || resultCode.equals(WScodes.POINT_DE_VENTE_INIEXISTANT) ) {
            return context.getResources().getString(R.string.account_not_found);
        }
        else if (resultCode.equals(WScodes.Msisdn_Deja_Inscrit)) {
            return context.getResources().getString(R.string.Msisdn_Deja_Inscrit);
        }else if (resultCode.equals(WScodes.Erreur_QrCode_TimeOut)) {
            return context.getResources().getString(R.string.time_out_qrcode);
        }else if (resultCode.equals(WScodes.MSISDN_Incorrect_1)) {
            return context.getResources().getString(R.string.erreur_013);
        }else if (resultCode.equals(WScodes.Erreur_Bin_NonAccepte)) {
            return context.getResources().getString(R.string.Erreur_Bin_NonAccepte);
        }else if (resultCode.equals(resultCode.equals(WScodes.Erreur_Bin_S_NonAccepte))) {
            return context.getResources().getString(R.string.bin_s_non_accepte);
        }
        else if (resultCode.equals(WScodes.Erreur_Bin_Inexistant)) {
            return context.getResources().getString(R.string.Erreur_Bin_Inexistant);
        }else if (resultCode.equals(WScodes.Erreur_Bin_S_Inexistant)) {
            return context.getResources().getString(R.string.bin_s_inexistant);
        }else if (resultCode.equals(WScodes.Erreur_Time_Out)) {
            return context.getResources().getString(R.string.time_out);
        }else if (resultCode.equals(WScodes.Erreur_AMOUNT)) {
            return context.getResources().getString(R.string.Erreur_AMOUNT);
        }else if (resultCode.equals(WScodes.Données_bancaires_invalide)) {
            return context.getResources().getString(R.string.Erreur_Données_bancaires_invalide);
        }else if (resultCode.equals(WScodes.Erreur_Donnees_Bancaire)) {
            return context.getResources().getString(R.string.Erreur_Données_bancaires_invalide);
        }
        else if (resultCode.equals(WScodes.Erreur_Recharge_Pay_Mob)) {
            return context.getResources().getString(R.string.Erreur_Recharge_Pay_Mob);
        }else if (resultCode.equals(WScodes.Categorie_abonnement_KO)) {
            return context.getResources().getString(R.string.Categorie_abonnement_KO);
        }else if (resultCode.equals(WScodes.Fraude_Req)) {
            return context.getResources().getString(R.string.error_servenu);
        }else if (resultCode.equals(WScodes.Erreur_Format_Hach)) {
            return context.getResources().getString(R.string.error_servenu);
        }else if (resultCode.equals(WScodes.Erreur_Format_Affiliation)) {
            return context.getResources().getString(R.string.error_servenu);
        }else if (resultCode.equals(WScodes.Erreur_Format_Type_Card)) {
            return context.getResources().getString(R.string.error_servenu);
        }else if (resultCode.equals(WScodes.Erreur_Profile_Impossible)) {
            return context.getResources().getString(R.string.profile_impossible);
        }else if (resultCode.equals(WScodes.Erreur_Format_Fees)) {
            return context.getResources().getString(R.string.error_servenu);
        }else if (resultCode.equals(WScodes.Erreur_MSISDN_NOT_OOredoo)) {
            return context.getResources().getString(R.string.msg_input_msisdn_not_ooredoo);
        }else if (resultCode.equals(WScodes.Erreur_format_identifiant_transaction_SMT)) {
            return context.getResources().getString(R.string.transaction_id_invalide);
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_NonCo_brandé)) {
            return context.getResources().getString(R.string.bin_non_co_brandee);
        }else if (resultCode.equals(WScodes.Erreur_Bin_S_Co_brandé)) {
            return context.getResources().getString(R.string.bin_s_co_brandee);
        }else if (resultCode.equals(WScodes.Carte_Expiree)) {
            return context.getResources().getString(R.string.carte_expire);
        }else if (resultCode.equals(WScodes.Erreur_Format_RF)) {
            return context.getResources().getString(R.string.ref_invalide);
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_NonAccepte_Receiver)) {
            return context.getResources().getString(R.string.bin_receiver_p_non_accepte);
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_Inexistant_Receiver)) {
            return context.getResources().getString(R.string.bin_receiver_p_inexistant);
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_NonCo_brandé_Receiver)) {
            return context.getResources().getString(R.string.bin_receiver_p_non_co_brandee);
        }else {
            return context.getResources().getString(R.string.error_servenu);
        }

    }
}
