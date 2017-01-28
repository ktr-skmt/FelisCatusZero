package time

import text.{StringNone, StringOption, StringSome}

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

/**
 * <pre>
 * Created on 2015/10/17
 * </pre>
  *
  * @author K.Sakamoto
 */
object RegnalYearParser {
  private val regex: Regex = """(皇紀|大化|白雉|朱鳥|大宝|慶雲|和銅|霊亀|養老|神亀|天平|天平感宝|天平勝宝|天平宝字|天平神護|神護景雲|宝亀|天応|延暦|大同|弘仁|天長|承和|嘉祥|仁寿|斉衡|天安|貞観|元慶|仁和|寛平|昌泰|延喜|延長|承平|天慶|天暦|天徳|応和|康保|安和|天禄|天延|貞元|天元|永観|寛和|永延|永祚|正暦|長徳|長保|寛弘|長和|寛仁|治安|万寿|長元|長暦|長久|寛徳|永承|天喜|康平|治暦|延久|承保|承暦|永保|応徳|寛治|嘉保|永長|承徳|康和|長治|嘉承|天仁|天永|永久|元永|保安|天治|大治|天承|長承|保延|永治|康治|天養|久安|仁平|久寿|保元|平治|永暦|応保|長寛|永万|仁安|嘉応|承安|安元|治承|養和|寿永|元暦|文治|建久|正治|建仁|元久|建永|承元|建暦|建保|承久|貞応|元仁|嘉禄|安貞|寛喜|貞永|天福|文暦|嘉禎|暦仁|延応|仁治|寛元|宝治|建長|康元|正嘉|正元|文応|弘長|文永|建治|弘安|正応|永仁|正安|乾元|嘉元|徳治|延慶|応長|正和|文保|元応|元亨|正中|嘉暦|元徳|元弘|正慶|建武|延元|興国|正平|建徳|文中|天授|弘和|元中|正慶|暦応|康永|貞和|観応|文和|延文|康安|貞治|応安|永和|康暦|永徳|至徳|嘉慶|康応|明徳|応永|正長|永享|嘉吉|文安|宝徳|享徳|康正|長禄|寛正|文正|応仁|文明|長享|延徳|明応|文亀|永正|大永|享禄|天文|弘治|永禄|元亀|天正|文禄|慶長|元和|寛永|正保|慶安|承応|明暦|万治|寛文|延宝|天和|貞享|元禄|宝永|正徳|享保|元文|寛保|延享|寛延|宝暦|明和|安永|天明|寛政|享和|文化|文政|天保|弘化|嘉永|安政|万延|文久|元治|慶応|明治|大正|昭和|平成)(\d+)年""".r

  def convertToRomanCalendar(text: StringOption): StringOption = {
    text map {
      str: String =>
        var t: String = str
        var mOpt: Option[Match] = regex.findFirstMatchIn(t)
        while (mOpt.nonEmpty) {
          val m: Match = mOpt.get
          val regnalYear: StringOption = StringOption(m.group(1))
          val year: Int = m.group(2).toInt
          convertToRomanCalendar(regnalYear, year) match {
            case Some(ry) =>
              t = t.replaceFirst(
                m.group(0),
                "%d年".format(ry))
              mOpt = regex.findFirstMatchIn(t)
            case None =>
              //Do nothing
          }
        }
        t
    }
  }

  private def convertToRomanCalendar(regnalYear: StringOption, year: Int): Option[Int] = {
    regnalYear match {
      case StringSome(ry) =>
        val diff: Int = ry match {
          case "皇紀" => -660
          //飛鳥時代（６つ）
          case "大化" => 644
          case "白雉" => 649
          case "朱鳥" => 685
          case "大宝" => 700
          case "慶雲" => 703
          case "和銅" => 707
          //奈良時代（１２つ）
          case "霊亀" => 714
          case "養老" => 716
          case "神亀" => 723
          case "天平" => 728
          case "天平感宝" => 748
          case "天平勝宝" => 748
          case "天平宝字" => 756
          case "天平神護" => 764
          case "神護景雲" => 766
          case "宝亀" => 769
          case "天応" => 780
          case "延暦" => 781
          //平安時代（８８つ）
          case "大同" => 805
          case "弘仁" => 809
          case "天長" => 823
          case "承和" => 833
          case "嘉祥" => 847
          case "仁寿" => 850
          case "斉衡" => 853
          case "天安" => 856
          case "貞観" => 858
          case "元慶" => 876
          case "仁和" => 884
          case "寛平" => 888
          case "昌泰" => 897
          case "延喜" => 900
          case "延長" => 922
          case "承平" => 930
          case "天慶" => 937
          case "天暦" => 946
          case "天徳" => 956
          case "応和" => 960
          case "康保" => 963
          case "安和" => 967
          case "天禄" => 969
          case "天延" => 972
          case "貞元" => 975
          case "天元" => 977
          case "永観" => 982
          case "寛和" => 984
          case "永延" => 986
          case "永祚" => 988
          case "正暦" => 989
          case "長徳" => 994
          case "長保" => 998
          case "寛弘" => 1003
          case "長和" => 1011
          case "寛仁" => 1016
          case "治安" => 1020
          case "万寿" => 1023
          case "長元" => 1027
          case "長暦" => 1036
          case "長久" => 1039
          case "寛徳" => 1043
          case "永承" => 1045
          case "天喜" => 1052
          case "康平" => 1057
          case "治暦" => 1064
          case "延久" => 1068
          case "承保" => 1073
          case "承暦" => 1076
          case "永保" => 1080
          case "応徳" => 1083
          case "寛治" => 1086
          case "嘉保" => 1093
          case "永長" => 1095
          case "承徳" => 1096
          case "康和" => 1098
          case "長治" => 1103
          case "嘉承" => 1105
          case "天仁" => 1107
          case "天永" => 1109
          case "永久" => 1112
          case "元永" => 1117
          case "保安" => 1119
          case "天治" => 1123
          case "大治" => 1125
          case "天承" => 1130
          case "長承" => 1131
          case "保延" => 1134
          case "永治" => 1140
          case "康治" => 1141
          case "天養" => 1143
          case "久安" => 1144
          case "仁平" => 1150
          case "久寿" => 1153
          case "保元" => 1155
          case "平治" => 1158
          case "永暦" => 1159
          case "応保" => 1160
          case "長寛" => 1162
          case "永万" => 1164
          case "仁安" => 1165
          case "嘉応" => 1168
          case "承安" => 1170
          case "安元" => 1174
          case "治承" => 1176
          case "養和" => 1180
          case "寿永" => 1181
          case "元暦" => 1183
          //鎌倉時代（４８つ）
          case "文治" => 1184
          case "建久" => 1189
          case "正治" => 1198
          case "建仁" => 1200
          case "元久" => 1203
          case "建永" => 1205
          case "承元" => 1206
          case "建暦" => 1210
          case "建保" => 1212
          case "承久" => 1218
          case "貞応" => 1221
          case "元仁" => 1223
          case "嘉禄" => 1224
          case "安貞" => 1226
          case "寛喜" => 1228
          case "貞永" => 1231
          case "天福" => 1232
          case "文暦" => 1233
          case "嘉禎" => 1234
          case "暦仁" => 1237
          case "延応" => 1238
          case "仁治" => 1239
          case "寛元" => 1242
          case "宝治" => 1246
          case "建長" => 1248
          case "康元" => 1255
          case "正嘉" => 1256
          case "正元" => 1258
          case "文応" => 1259
          case "弘長" => 1260
          case "文永" => 1263
          case "建治" => 1274
          case "弘安" => 1277
          case "正応" => 1287
          case "永仁" => 1292
          case "正安" => 1298
          case "乾元" => 1301
          case "嘉元" => 1302
          case "徳治" => 1305
          case "延慶" => 1307
          case "応長" => 1310
          case "正和" => 1311
          case "文保" => 1316
          case "元応" => 1318
          case "元亨" => 1320
          case "正中" => 1323
          case "嘉暦" => 1325
          case "元徳" => 1328
          //大覚寺統（１つ）
          case "元弘" => 1330
          //持明院統（１つ）
          case "正慶" => 1331
          //南北朝時代・室町時代（１つ）
          case "建武" => 1333
          //南朝（大覚寺統）（８つ）
          case "延元" => 1335
          case "興国" => 1339
          case "正平" => 1345
          case "建徳" => 1369
          case "文中" => 1371
          case "天授" => 1374
          case "弘和" => 1380
          case "元中" => 1383
          //北朝（持明院統）（１６つ）
          case "暦応" => 1337
          case "康永" => 1341
          case "貞和" => 1344
          case "観応" => 1349
          case "文和" => 1351
          case "延文" => 1355
          case "康安" => 1360
          case "貞治" => 1361
          case "応安" => 1367
          case "永和" => 1374
          case "康暦" => 1378
          case "永徳" => 1380
          case "至徳" => 1383
          case "嘉慶" => 1386
          case "康応" => 1388
          case "明徳" => 1389
          //南北朝合一後（１１つ）
          case "応永" => 1393
          case "正長" => 1427
          case "永享" => 1428
          case "嘉吉" => 1440
          case "文安" => 1443
          case "宝徳" => 1448
          case "享徳" => 1451
          case "康正" => 1454
          case "長禄" => 1456
          case "寛正" => 1459
          case "文正" => 1465
          //戦国時代（１３つ）
          case "応仁" => 1466
          case "文明" => 1468
          case "長享" => 1486
          case "延徳" => 1488
          case "明応" => 1491
          case "文亀" => 1500
          case "永正" => 1503
          case "大永" => 1520
          case "享禄" => 1527
          case "天文" => 1531
          case "弘治" => 1554
          case "永禄" => 1557
          case "元亀" => 1569
          //安土桃山時代（３つ）
          case "天正" => 1572
          case "文禄" => 1591
          case "慶長" => 1595
          //江戸時代（３５つ）
          case "元和" => 1614
          case "寛永" => 1623
          case "正保" => 1643
          case "慶安" => 1647
          case "承応" => 1651
          case "明暦" => 1654
          case "万治" => 1657
          case "寛文" => 1660
          case "延宝" => 1672
          case "天和" => 1680
          case "貞享" => 1683
          case "元禄" => 1687
          case "宝永" => 1703
          case "正徳" => 1710
          case "享保" => 1715
          case "元文" => 1735
          case "寛保" => 1740
          case "延享" => 1743
          case "寛延" => 1747
          case "宝暦" => 1750
          case "明和" => 1763
          case "安永" => 1771
          case "天明" => 1780
          case "寛政" => 1788
          case "享和" => 1800
          case "文化" => 1803
          case "文政" => 1817
          case "天保" => 1829
          case "弘化" => 1843
          case "嘉永" => 1847
          case "安政" => 1853
          case "万延" => 1859
          case "文久" => 1860
          case "元治" => 1863
          case "慶応" => 1864
          //明治時代以降（近代・現代）（４つ）
          case "明治" => 1867
          case "大正" => 1911
          case "昭和" => 1925
          case "平成" => 1988
          case _ => 0
        }
        Option(year + diff)
      case StringNone =>
        None
    }
  }
}