
/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class TextAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TextAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TextAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TextAnnotation(addr, TextAnnotation_Type.this);
  			   TextAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TextAnnotation(addr, TextAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TextAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.TextAnnotation");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public long getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getLongValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, long v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "jeqa.types.TextAnnotation");
    ll_cas.ll_setLongValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "jeqa.types.TextAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_docno;
  /** @generated */
  final int     casFeatCode_docno;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDocno(int addr) {
        if (featOkTst && casFeat_docno == null)
      jcas.throwFeatMissing("docno", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_docno);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocno(int addr, String v) {
        if (featOkTst && casFeat_docno == null)
      jcas.throwFeatMissing("docno", "jeqa.types.TextAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_docno, v);}
    
  
 
  /** @generated */
  final Feature casFeat_title;
  /** @generated */
  final int     casFeatCode_title;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTitle(int addr) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_title);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTitle(int addr, String v) {
        if (featOkTst && casFeat_title == null)
      jcas.throwFeatMissing("title", "jeqa.types.TextAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_title, v);}
    
  
 
  /** @generated */
  final Feature casFeat_scoreList;
  /** @generated */
  final int     casFeatCode_scoreList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getScoreList(int addr) {
        if (featOkTst && casFeat_scoreList == null)
      jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_scoreList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setScoreList(int addr, int v) {
        if (featOkTst && casFeat_scoreList == null)
      jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_scoreList, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getScoreList(int addr, int i) {
        if (featOkTst && casFeat_scoreList == null)
      jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_scoreList), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_scoreList), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_scoreList), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setScoreList(int addr, int i, int v) {
        if (featOkTst && casFeat_scoreList == null)
      jcas.throwFeatMissing("scoreList", "jeqa.types.TextAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_scoreList), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_scoreList), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_scoreList), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_morphemeList;
  /** @generated */
  final int     casFeatCode_morphemeList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getMorphemeList(int addr) {
        if (featOkTst && casFeat_morphemeList == null)
      jcas.throwFeatMissing("morphemeList", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_morphemeList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMorphemeList(int addr, int v) {
        if (featOkTst && casFeat_morphemeList == null)
      jcas.throwFeatMissing("morphemeList", "jeqa.types.TextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_morphemeList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_contentWordList;
  /** @generated */
  final int     casFeatCode_contentWordList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getContentWordList(int addr) {
        if (featOkTst && casFeat_contentWordList == null)
      jcas.throwFeatMissing("contentWordList", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_contentWordList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContentWordList(int addr, int v) {
        if (featOkTst && casFeat_contentWordList == null)
      jcas.throwFeatMissing("contentWordList", "jeqa.types.TextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_contentWordList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_beginTime;
  /** @generated */
  final int     casFeatCode_beginTime;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getBeginTime(int addr) {
        if (featOkTst && casFeat_beginTime == null)
      jcas.throwFeatMissing("beginTime", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_beginTime);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setBeginTime(int addr, int v) {
        if (featOkTst && casFeat_beginTime == null)
      jcas.throwFeatMissing("beginTime", "jeqa.types.TextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_beginTime, v);}
    
  
 
  /** @generated */
  final Feature casFeat_endTime;
  /** @generated */
  final int     casFeatCode_endTime;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getEndTime(int addr) {
        if (featOkTst && casFeat_endTime == null)
      jcas.throwFeatMissing("endTime", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_endTime);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEndTime(int addr, int v) {
        if (featOkTst && casFeat_endTime == null)
      jcas.throwFeatMissing("endTime", "jeqa.types.TextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_endTime, v);}
    
  
 
  /** @generated */
  final Feature casFeat_geography;
  /** @generated */
  final int     casFeatCode_geography;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getGeography(int addr) {
        if (featOkTst && casFeat_geography == null)
      jcas.throwFeatMissing("geography", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_geography);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setGeography(int addr, int v) {
        if (featOkTst && casFeat_geography == null)
      jcas.throwFeatMissing("geography", "jeqa.types.TextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_geography, v);}
    
  
 
  /** @generated */
  final Feature casFeat_semanticTypeList;
  /** @generated */
  final int     casFeatCode_semanticTypeList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSemanticTypeList(int addr) {
        if (featOkTst && casFeat_semanticTypeList == null)
      jcas.throwFeatMissing("semanticTypeList", "jeqa.types.TextAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_semanticTypeList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSemanticTypeList(int addr, int v) {
        if (featOkTst && casFeat_semanticTypeList == null)
      jcas.throwFeatMissing("semanticTypeList", "jeqa.types.TextAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_semanticTypeList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TextAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Long", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_docno = jcas.getRequiredFeatureDE(casType, "docno", "uima.cas.String", featOkTst);
    casFeatCode_docno  = (null == casFeat_docno) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_docno).getCode();

 
    casFeat_title = jcas.getRequiredFeatureDE(casType, "title", "uima.cas.String", featOkTst);
    casFeatCode_title  = (null == casFeat_title) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_title).getCode();

 
    casFeat_scoreList = jcas.getRequiredFeatureDE(casType, "scoreList", "uima.cas.FSArray", featOkTst);
    casFeatCode_scoreList  = (null == casFeat_scoreList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_scoreList).getCode();

 
    casFeat_morphemeList = jcas.getRequiredFeatureDE(casType, "morphemeList", "uima.cas.FSList", featOkTst);
    casFeatCode_morphemeList  = (null == casFeat_morphemeList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_morphemeList).getCode();

 
    casFeat_contentWordList = jcas.getRequiredFeatureDE(casType, "contentWordList", "uima.cas.StringList", featOkTst);
    casFeatCode_contentWordList  = (null == casFeat_contentWordList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_contentWordList).getCode();

 
    casFeat_beginTime = jcas.getRequiredFeatureDE(casType, "beginTime", "jeqa.types.Time", featOkTst);
    casFeatCode_beginTime  = (null == casFeat_beginTime) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_beginTime).getCode();

 
    casFeat_endTime = jcas.getRequiredFeatureDE(casType, "endTime", "jeqa.types.Time", featOkTst);
    casFeatCode_endTime  = (null == casFeat_endTime) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_endTime).getCode();

 
    casFeat_geography = jcas.getRequiredFeatureDE(casType, "geography", "jeqa.types.Geography", featOkTst);
    casFeatCode_geography  = (null == casFeat_geography) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_geography).getCode();

 
    casFeat_semanticTypeList = jcas.getRequiredFeatureDE(casType, "semanticTypeList", "uima.cas.FSList", featOkTst);
    casFeatCode_semanticTypeList  = (null == casFeat_semanticTypeList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_semanticTypeList).getCode();

  }
}



    