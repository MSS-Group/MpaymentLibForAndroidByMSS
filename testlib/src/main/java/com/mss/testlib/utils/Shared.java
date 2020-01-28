package com.mss.testlib.utils;

import android.content.Context;
import android.widget.Toast;

import com.mss.testlib.R;

public class Shared {
    public static void CheckError(Context context, String resultCode) {

        if (resultCode.equals(WScodes.BD_KO_USSD)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Erreur_Pwd_Incorrect)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_5023),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Erreur_Statut_MSISDN)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_111),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.MSISDN_inexistant) || resultCode.equals(WScodes.CheckMsisdnONP_KO) ) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_111),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.MSISDN_Bloqué)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_423_blocked),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.MSISDN_Désinscrit)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_140),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.MSISDN_Preinscrit)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_139),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Msisdn_Non_Inscrit)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_169),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Erreur_Solde)) {
            Toast.makeText(context,context.getResources().getString(R.string.solde_insufisant),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Solde_Insuffisant)) {
            Toast.makeText(context,context.getResources().getString(R.string.solde_insufisant),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.MSISDN_Incorrect)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_013),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Erreur_Format_IDM)) {
            Toast.makeText(context,context.getResources().getString(R.string.code_comercant_error),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Service_Incorrect)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Erreur_Format_Pwd)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_451),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.ERR_format_montant)) {
            Toast.makeText(context,context.getResources().getString(R.string.montant_invalide),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.User_inexistant)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_169),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.MSISDN_Déja_Inscrit_avec_Autre_Service)) {
            Toast.makeText(context,context.getResources().getString(R.string.Msisdn_Deja_Inscrit_autr_opr),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Caisse_inexistante)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_caisse),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Merchant_inexistant)) {
            Toast.makeText(context,context.getResources().getString(R.string.comm_inexis),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Transaction_inexistante)) {
            Toast.makeText(context,context.getResources().getString(R.string.trans_inexis),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Transaction_Refusee)) {
            Toast.makeText(context,context.getResources().getString(R.string.trans_refusee),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.ERR_transaction_Mobicash_KO)) {
            Toast.makeText(context,context.getResources().getString(R.string.trans_non_effec),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Historique_vide)) {
            Toast.makeText(context,context.getResources().getString(R.string.hist_vide),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.User_non_Active)) {
            Toast.makeText(context,context.getResources().getString(R.string.user_non_active),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Format_ReferenceNumber)) {
            Toast.makeText(context,context.getResources().getString(R.string.format_referencenumber),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.RefeNum_Inexistant)) {
            Toast.makeText(context,context.getResources().getString(R.string.refenum_inexistant),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Favori_Existant)) {
            Toast.makeText(context,context.getResources().getString(R.string.favori_existant),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Pas_de_facture)) {
            Toast.makeText(context,context.getResources().getString(R.string.pas_de_facture),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.BD_STEG_KO)) {
            Toast.makeText(context,context.getResources().getString(R.string.bd_steg_ko),Toast.LENGTH_SHORT).show();
        } /*else if (resultCode.equals(WScodes.Categorie_abonnement_KO)) {
            Toast.makeText(context,)(context.getResources().getString(R.string.categorie_abonnement_ko),Toast.LENGTH_SHORT).show();

        }*/ else if (resultCode.equals(WScodes.Paiement_en_cours)) {
            Toast.makeText(context,context.getResources().getString(R.string.paiement_en_cours),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Facture_Deja_Payee)) {
            Toast.makeText(context,context.getResources().getString(R.string.facture_deja_payee),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Abonnement_Resilie)) {
            Toast.makeText(context,context.getResources().getString(R.string.abonnement_resilie),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Id_Eleve_inexistant)) {
            Toast.makeText(context,context.getResources().getString(R.string.ID_ELEVE_INEXISTENT),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Eleve_Inscrit_par_web)) {
            Toast.makeText(context,context.getResources().getString(R.string.ELEVE_INSCRITt_PAR_WEB),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Eleve_Inscrit_par_phone)) {
            Toast.makeText(context,context.getResources().getString(R.string.ELEVE_INSCRIT_PAR_PHONE),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.Eleve_Inscrit)) {
            Toast.makeText(context,context.getResources().getString(R.string.ELEVE_DEJA_INSCRIT),Toast.LENGTH_SHORT).show();
        } else if (resultCode.equals(WScodes.CB_DEJA_UTILISER)) {
            Toast.makeText(context,context.getResources().getString(R.string.CB_DEJA_UTILISER),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.ERR_ACCOUTN_CASUAL_BET_NUM) || resultCode.equals(WScodes.COMPTE_INEXISTANT) || resultCode.equals(WScodes.POINT_DE_VENTE_INIEXISTANT) ) {
            Toast.makeText(context,context.getResources().getString(R.string.account_not_found),Toast.LENGTH_SHORT).show();
        }
        else if (resultCode.equals(WScodes.Msisdn_Deja_Inscrit)) {
            Toast.makeText(context,context.getResources().getString(R.string.Msisdn_Deja_Inscrit),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_QrCode_TimeOut)) {
            Toast.makeText(context,context.getResources().getString(R.string.time_out_qrcode),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.MSISDN_Incorrect_1)) {
            Toast.makeText(context,context.getResources().getString(R.string.erreur_013),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Bin_NonAccepte)) {
            Toast.makeText(context,context.getResources().getString(R.string.Erreur_Bin_NonAccepte),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(resultCode.equals(WScodes.Erreur_Bin_S_NonAccepte))) {
            Toast.makeText(context,context.getResources().getString(R.string.bin_s_non_accepte),Toast.LENGTH_SHORT).show();
        }
        else if (resultCode.equals(WScodes.Erreur_Bin_Inexistant)) {
            Toast.makeText(context,context.getResources().getString(R.string.Erreur_Bin_Inexistant),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Bin_S_Inexistant)) {
            Toast.makeText(context,context.getResources().getString(R.string.bin_s_inexistant),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Time_Out)) {
            Toast.makeText(context,context.getResources().getString(R.string.time_out),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_AMOUNT)) {
            Toast.makeText(context,context.getResources().getString(R.string.Erreur_AMOUNT),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Données_bancaires_invalide)) {
            Toast.makeText(context,context.getResources().getString(R.string.Erreur_Données_bancaires_invalide),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Donnees_Bancaire)) {
            Toast.makeText(context,context.getResources().getString(R.string.Erreur_Données_bancaires_invalide),Toast.LENGTH_SHORT).show();
        }
        else if (resultCode.equals(WScodes.Erreur_Recharge_Pay_Mob)) {
            Toast.makeText(context,context.getResources().getString(R.string.Erreur_Recharge_Pay_Mob),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Categorie_abonnement_KO)) {
            Toast.makeText(context,context.getResources().getString(R.string.Categorie_abonnement_KO),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Fraude_Req)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Format_Hach)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Format_Affiliation)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Format_Type_Card)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Profile_Impossible)) {
            Toast.makeText(context,context.getResources().getString(R.string.profile_impossible),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Format_Fees)) {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_MSISDN_NOT_OOredoo)) {
            Toast.makeText(context,context.getResources().getString(R.string.msg_input_msisdn_not_ooredoo),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_format_identifiant_transaction_SMT)) {
            Toast.makeText(context,context.getResources().getString(R.string.transaction_id_invalide),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_NonCo_brandé)) {
            Toast.makeText(context,context.getResources().getString(R.string.bin_non_co_brandee),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Bin_S_Co_brandé)) {
            Toast.makeText(context,context.getResources().getString(R.string.bin_s_co_brandee),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Carte_Expiree)) {
            Toast.makeText(context,context.getResources().getString(R.string.carte_expire),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Format_RF)) {
            Toast.makeText(context,context.getResources().getString(R.string.ref_invalide),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_NonAccepte_Receiver)) {
            Toast.makeText(context,context.getResources().getString(R.string.bin_receiver_p_non_accepte),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_Inexistant_Receiver)) {
            Toast.makeText(context,context.getResources().getString(R.string.bin_receiver_p_inexistant),Toast.LENGTH_SHORT).show();
        }else if (resultCode.equals(WScodes.Erreur_Bin_P_NonCo_brandé_Receiver)) {
            Toast.makeText(context,context.getResources().getString(R.string.bin_receiver_p_non_co_brandee),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,context.getResources().getString(R.string.error_servenu),Toast.LENGTH_SHORT).show();
        }

    }
}
