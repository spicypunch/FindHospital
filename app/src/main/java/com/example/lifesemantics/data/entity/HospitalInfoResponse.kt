package com.example.lifesemantics.data.entity

import android.os.Parcelable
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import kotlinx.parcelize.Parcelize

@Xml(name = "response")
data class HospitalInfoResponse(
    @Element(name = "header")
    var header: Header? = null,

    @Element(name = "body")
    var body: Body? = null
)

@Xml(name = "header")
data class Header(
    @PropertyElement(name = "resultCode")
    val resultCode: String?,

    @PropertyElement(name = "resultMsg")
    val resultMsg: String?
)

@Xml(name = "body")
data class Body(
    @Element(name = "items")
    val items: Items?,

    @PropertyElement(name = "numOfRows")
    val numOfRows: Int?,

    @PropertyElement(name = "pageNo")
    val pageNo: Int?,

    @PropertyElement(name = "totalCount")
    val totalCount: Int?
)

@Xml(name = "items")
data class Items(
    @Element(name = "item")
    val itemList: List<Item>?
)

@Xml(name = "item")
@Parcelize
data class Item(
    @PropertyElement(name = "cmdcResdntCnt")
    val cmdcResdntCnt: String?,

    @PropertyElement(name = "cmdcSdrCnt")
    val cmdcSdrCnt: String?,

    @PropertyElement(name = "pnursCnt")
    val pnursCnt: String?,

    @PropertyElement(name = "XPos")
    val xPos: String?,

    @PropertyElement(name = "YPos")
    val yPos: String?,

    @PropertyElement(name = "distance")
    val distance: String?,

    @PropertyElement(name = "detyGdrCnt")
    val detyGdrCnt: String?,

    @PropertyElement(name = "detyIntnCnt")
    val detyIntnCnt: String?,

    @PropertyElement(name = "detyResdntCnt")
    val detyResdntCnt: String?,

    @PropertyElement(name = "detySdrCnt")
    val detySdrCnt: String?,

    @PropertyElement(name = "cmdcGdrCnt")
    val cmdcGdrCnt: String?,

    @PropertyElement(name = "cmdcIntnCnt")
    val cmdcIntnCnt: String?,

    @PropertyElement(name = "mdeptResdntCnt")
    val mdeptResdntCnt: String?,

    @PropertyElement(name = "drTotCnt")
    val drTotCnt: String?,

    @PropertyElement(name = "mdeptGdrCnt")
    val mdeptGdrCnt: String?,

    @PropertyElement(name = "mdeptIntnCnt")
    val mdeptIntnCnt: String?,

    @PropertyElement(name = "telno")
    val telno: String?,

    @PropertyElement(name = "hospUrl")
    val hospUrl: String?,

    @PropertyElement(name = "estbDd")
    val estbDd: String?,

    @PropertyElement(name = "sgguCdNm")
    val sgguCdNm: String?,

    @PropertyElement(name = "emdongNm")
    val emdongNm: String?,

    @PropertyElement(name = "postNo")
    val postNo: String?,

    @PropertyElement(name = "addr")
    val addr: String?,

    @PropertyElement(name = "sidoCdNm")
    val sidoCdNm: String?,

    @PropertyElement(name = "sgguCd")
    val sgguCd: Int?,

    @PropertyElement(name = "ykiho")
    val ykiho: String?,

    @PropertyElement(name = "yadmNm")
    val yadmNm: String?,

    @PropertyElement(name = "clCd")
    val clCd: String?,

    @PropertyElement(name = "clCdNm")
    val clCdNm: String?,

    @PropertyElement(name = "sidoCd")
    val sidoCd: Int?,

    @PropertyElement(name = "mdeptSdrCnt")
    val mdeptSdrCnt: String?
) : Parcelable