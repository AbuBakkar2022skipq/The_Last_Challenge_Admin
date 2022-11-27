package com.example.thelastchallengeadmin.GameManagement

import com.example.thelastchallengeadmin.SelectResources.selectresourcesrecyclerviewmodel
import java.util.ArrayList

data class game (
    var gameNameEn:String="",
    var gameNameSp:String="",

    var gameExplainEn:String="",
    var gameExplainSp:String="",

    var gameExplainEn18:String="",
    var gameExplainSp18:String="",

    var gameexplain:String="",
    var cups:String="",
    var pingpongballs:String="",
    var bottles:String="",
    var baloons:String="",
    var cards:String="",
    var cookies:String="",
    var punishment:String="",
    var timer:Int=0,
    var gameiconurl:String="",
    var documentId:String="",
    var onlinestatus:Boolean=false,
    var listOfModelsdata:List<selectresourcesrecyclerviewmodell> = listOf()
)

data class selectresourcesrecyclerviewmodell (
    var resName_eng:String="",
    var resName_sp:String="",
    var resdesc_en:String="",
    var resdesc_sp:String="",
    var resIconURL:String="",
    var docID:String="",
    var onlinestatus:Boolean=false
)
