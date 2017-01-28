
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

/** 試験問題
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Exam_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Exam_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Exam_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Exam(addr, Exam_Type.this);
  			   Exam_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Exam(addr, Exam_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Exam.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Exam");
 
  /** @generated */
  final Feature casFeat_questionSet;
  /** @generated */
  final int     casFeatCode_questionSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuestionSet(int addr) {
        if (featOkTst && casFeat_questionSet == null)
      jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    return ll_cas.ll_getRefValue(addr, casFeatCode_questionSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuestionSet(int addr, int v) {
        if (featOkTst && casFeat_questionSet == null)
      jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    ll_cas.ll_setRefValue(addr, casFeatCode_questionSet, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getQuestionSet(int addr, int i) {
        if (featOkTst && casFeat_questionSet == null)
      jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_questionSet), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_questionSet), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_questionSet), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setQuestionSet(int addr, int i, int v) {
        if (featOkTst && casFeat_questionSet == null)
      jcas.throwFeatMissing("questionSet", "jeqa.types.Exam");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_questionSet), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_questionSet), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_questionSet), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_label;
  /** @generated */
  final int     casFeatCode_label;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getLabel(int addr) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "jeqa.types.Exam");
    return ll_cas.ll_getStringValue(addr, casFeatCode_label);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLabel(int addr, String v) {
        if (featOkTst && casFeat_label == null)
      jcas.throwFeatMissing("label", "jeqa.types.Exam");
    ll_cas.ll_setStringValue(addr, casFeatCode_label, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dir;
  /** @generated */
  final int     casFeatCode_dir;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDir(int addr) {
        if (featOkTst && casFeat_dir == null)
      jcas.throwFeatMissing("dir", "jeqa.types.Exam");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dir);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDir(int addr, String v) {
        if (featOkTst && casFeat_dir == null)
      jcas.throwFeatMissing("dir", "jeqa.types.Exam");
    ll_cas.ll_setStringValue(addr, casFeatCode_dir, v);}
    
  
 
  /** @generated */
  final Feature casFeat_lang;
  /** @generated */
  final int     casFeatCode_lang;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getLang(int addr) {
        if (featOkTst && casFeat_lang == null)
      jcas.throwFeatMissing("lang", "jeqa.types.Exam");
    return ll_cas.ll_getStringValue(addr, casFeatCode_lang);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLang(int addr, String v) {
        if (featOkTst && casFeat_lang == null)
      jcas.throwFeatMissing("lang", "jeqa.types.Exam");
    ll_cas.ll_setStringValue(addr, casFeatCode_lang, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Exam_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_questionSet = jcas.getRequiredFeatureDE(casType, "questionSet", "uima.cas.FSArray", featOkTst);
    casFeatCode_questionSet  = (null == casFeat_questionSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_questionSet).getCode();

 
    casFeat_label = jcas.getRequiredFeatureDE(casType, "label", "uima.cas.String", featOkTst);
    casFeatCode_label  = (null == casFeat_label) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_label).getCode();

 
    casFeat_dir = jcas.getRequiredFeatureDE(casType, "dir", "uima.cas.String", featOkTst);
    casFeatCode_dir  = (null == casFeat_dir) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dir).getCode();

 
    casFeat_lang = jcas.getRequiredFeatureDE(casType, "lang", "uima.cas.String", featOkTst);
    casFeatCode_lang  = (null == casFeat_lang) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lang).getCode();

  }
}



    