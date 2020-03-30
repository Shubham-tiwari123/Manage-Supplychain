package com.project.server2.utils;

import java.math.BigInteger;

public class ConstantClass {

    final static public String DATABASE_NAME="shipChainServer";
    final static public String STORE_DATA_COLLECTION="storeItems";
    final static public String REGISTER_COMPANY="registerCompany";
    final static public String STORE_KEYS="storeServerKeys";
    final static public String IP_ADDRESS="127.0.0.1";
    final static public int PORT_NUMBER = 27017;

    final static public String ADMIN_LOGIN_EMAIL = "s@gmail.com";
    final static public String ADMIN_LOGIN_PASS = "qwertyuiop";
    final static public int SUCCESSFUL= 200;
    final static public int FAILED = 400;
    final static public int BAD_REQUEST=401;
    final static public int INTERNAL_SERVER_ERROR=500;

    // server keys: encrypt using public
    final static public BigInteger pubMod = new BigInteger("4214777907657253113000721125232472745151580275285647620895837240116389083817917878012365856944153092973868671924564389124521775170086968208302639999087780442035711202814704935797100667846856044291609671826055542255797138350990715705296927176437820167719797414475944743179643064967001415309590173778978994981565503683209815493080469926827684603043186212402541461555174760220150526056039715051302004523664386280764941286651600519216673399923121960013752645423519522577190537942240232410245814356233811170598121245760218272339690452606653687518633839603065406334786701945064057361092780039613315761236874042648446366066611886311596584361240596079231203346311092661161817519019821952137525442766196358670410972718999957615697381937635782535051583752394240837340159209718878221683849030276452271788676950559026334485190010556958833951179653367985903385866522528207314896706713176659703852530064861393257095252259354783203190648803");
    final static public BigInteger pubExpo = new BigInteger("65537");

    // server keys: decrypt using private
    final static public BigInteger priMod = new BigInteger("4214777907657253113000721125232472745151580275285647620895837240116389083817917878012365856944153092973868671924564389124521775170086968208302639999087780442035711202814704935797100667846856044291609671826055542255797138350990715705296927176437820167719797414475944743179643064967001415309590173778978994981565503683209815493080469926827684603043186212402541461555174760220150526056039715051302004523664386280764941286651600519216673399923121960013752645423519522577190537942240232410245814356233811170598121245760218272339690452606653687518633839603065406334786701945064057361092780039613315761236874042648446366066611886311596584361240596079231203346311092661161817519019821952137525442766196358670410972718999957615697381937635782535051583752394240837340159209718878221683849030276452271788676950559026334485190010556958833951179653367985903385866522528207314896706713176659703852530064861393257095252259354783203190648803");
    final static public BigInteger priExpo = new BigInteger("3588706178236569238928639398053350689304491852258872217849909359186028406170673870162595778708174479975095284049232373192647879793722523154244227188139468151219566909371288963903593564966206280170901977619322693577032697777774141596151473675932423531731695611983866648746668939855175137359136836858791001693109514267215071244409667559650860677464880556395419665802552176172312428932955859732559538221607947926198105706360263853599170072821613006586926852311262892228639866395069073759328843149393710929383919142164612116762980114442396588258722512951970719180347257510390315980692213681172745936555730554326479670367176652197763126268340667647327645694743640120744240070437353982407109707527093678638342296532303708765250716861648351613317081033184420294726907507332737319844138076528635263325640987176797326746711399018827246255539663318639697593856442650188510417370547486181646621359307980239990292407352904419775849540593");

    // client keys: encrypt using public
    final static public BigInteger clientPubMod = new BigInteger("4156343760760005478618042312828033927860123414377155349803612869115825398688964086509384194088221212426661366648082907493149276638006326381747099781268514943532882976172098831010786048366918706497340087743055138869544169737353879784857704425139658562565530843044838631629595936794444663233977540758132291701586738225270529224871505377843321282032373450698242311308310948568673264954438213112616932908915665883820929118505684640131362919836945534327671472283758132411330886514879043837354032251805935744733982434094946823351261215293971619235370078207386766563513241003090760612688726290779695484894053971668489050897070893020570208470187022466980078192269972459235866877896097285023560367593721526514647283043920143333681651211642220439796465304373588264984594667207800284393305405960764426157612868044024959964625132135588775223613878204956629775917990376133094007028768999476925638443013347851308497785909789529885037030483");
    final static public BigInteger clientPubExpo = new BigInteger("65537");

    // client keys: decrypt using private
    final static public BigInteger clientPriMod = new BigInteger("4156343760760005478618042312828033927860123414377155349803612869115825398688964086509384194088221212426661366648082907493149276638006326381747099781268514943532882976172098831010786048366918706497340087743055138869544169737353879784857704425139658562565530843044838631629595936794444663233977540758132291701586738225270529224871505377843321282032373450698242311308310948568673264954438213112616932908915665883820929118505684640131362919836945534327671472283758132411330886514879043837354032251805935744733982434094946823351261215293971619235370078207386766563513241003090760612688726290779695484894053971668489050897070893020570208470187022466980078192269972459235866877896097285023560367593721526514647283043920143333681651211642220439796465304373588264984594667207800284393305405960764426157612868044024959964625132135588775223613878204956629775917990376133094007028768999476925638443013347851308497785909789529885037030483");
    final static public BigInteger clientPriExpo = new BigInteger("4062799548374314829351801679118505721753745611972310695013794461469063379324845771243178208365955526046001197342104265068985900483451504961586320798749158556427720970131940664238719743511017382175452045424654749321158103082447537219853735460629824478311076748510588712016954924743667150099868306697704668675512300291244345075357712094166636372881851595261162380747257518458372319445675279741527167188167834778054173385869221499551327820476897093551723299165999560804973139361363487972293673535865176438488198449700652143584116012421412623144087917130894027268857758145053421737343053694218980648793246443456695473479241860472867898344342315417302779877721799805625125323069101837941290843432754612574213769852771903996682621988908500735819985004449908237182250547859072247502756113777883823166601032178859304383396170498041052867127964968259602316939931350337517300419812755241686599484399982364957269212858120903733859166241");


}