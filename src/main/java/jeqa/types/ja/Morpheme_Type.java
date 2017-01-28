
/* First created by JCasGen Wed Jan 25 19:24:52 JST 2017 */
package jeqa.types.ja;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import jeqa.types.Analysis_Type;

/** 形態素
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Morpheme_Type extends Analysis_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Morpheme_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Morpheme_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Morpheme(addr, Morpheme_Type.this);
  			   Morpheme_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Morpheme(addr, Morpheme_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Morpheme.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.ja.Morpheme");
 
  /** @generated */
  final Feature casFeat_originalText;
  /** @generated */
  final int     casFeatCode_originalText;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getOriginalText(int addr) {
        if (featOkTst && casFeat_originalText == null)
      jcas.throwFeatMissing("originalText", "jeqa.types.ja.Morpheme");
    return ll_cas.ll_getStringValue(addr, casFeatCode_originalText);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOriginalText(int addr, String v) {
        if (featOkTst && casFeat_originalText == null)
      jcas.throwFeatMissing("originalText", "jeqa.types.ja.Morpheme");
    ll_cas.ll_setStringValue(addr, casFeatCode_originalText, v);}
    
  
 
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
      jcas.throwFeatMissing("text", "jeqa.types.ja.Morpheme");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "jeqa.types.ja.Morpheme");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_pos;
  /** @generated */
  final int     casFeatCode_pos;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPos(int addr) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "jeqa.types.ja.Morpheme");
    return ll_cas.ll_getStringValue(addr, casFeatCode_pos);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPos(int addr, String v) {
        if (featOkTst && casFeat_pos == null)
      jcas.throwFeatMissing("pos", "jeqa.types.ja.Morpheme");
    ll_cas.ll_setStringValue(addr, casFeatCode_pos, v);}
    
  
 
  /** @generated */
  final Feature casFeat_wordEmbeddingList;
  /** @generated */
  final int     casFeatCode_wordEmbeddingList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getWordEmbeddingList(int addr) {
        if (featOkTst && casFeat_wordEmbeddingList == null)
      jcas.throwFeatMissing("wordEmbeddingList", "jeqa.types.ja.Morpheme");
    return ll_cas.ll_getRefValue(addr, casFeatCode_wordEmbeddingList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWordEmbeddingList(int addr, int v) {
        if (featOkTst && casFeat_wordEmbeddingList == null)
      jcas.throwFeatMissing("wordEmbeddingList", "jeqa.types.ja.Morpheme");
    ll_cas.ll_setRefValue(addr, casFeatCode_wordEmbeddingList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_semanticType;
  /** @generated */
  final int     casFeatCode_semanticType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSemanticType(int addr) {
        if (featOkTst && casFeat_semanticType == null)
      jcas.throwFeatMissing("semanticType", "jeqa.types.ja.Morpheme");
    return ll_cas.ll_getRefValue(addr, casFeatCode_semanticType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSemanticType(int addr, int v) {
        if (featOkTst && casFeat_semanticType == null)
      jcas.throwFeatMissing("semanticType", "jeqa.types.ja.Morpheme");
    ll_cas.ll_setRefValue(addr, casFeatCode_semanticType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_isTerm;
  /** @generated */
  final int     casFeatCode_isTerm;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getIsTerm(int addr) {
        if (featOkTst && casFeat_isTerm == null)
      jcas.throwFeatMissing("isTerm", "jeqa.types.ja.Morpheme");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isTerm);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIsTerm(int addr, boolean v) {
        if (featOkTst && casFeat_isTerm == null)
      jcas.throwFeatMissing("isTerm", "jeqa.types.ja.Morpheme");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isTerm, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Morpheme_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_originalText = jcas.getRequiredFeatureDE(casType, "originalText", "uima.cas.String", featOkTst);
    casFeatCode_originalText  = (null == casFeat_originalText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_originalText).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_pos = jcas.getRequiredFeatureDE(casType, "pos", "uima.cas.String", featOkTst);
    casFeatCode_pos  = (null == casFeat_pos) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pos).getCode();

 
    casFeat_wordEmbeddingList = jcas.getRequiredFeatureDE(casType, "wordEmbeddingList", "uima.cas.FSList", featOkTst);
    casFeatCode_wordEmbeddingList  = (null == casFeat_wordEmbeddingList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_wordEmbeddingList).getCode();

 
    casFeat_semanticType = jcas.getRequiredFeatureDE(casType, "semanticType", "jeqa.types.ja.SemanticType", featOkTst);
    casFeatCode_semanticType  = (null == casFeat_semanticType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_semanticType).getCode();

 
    casFeat_isTerm = jcas.getRequiredFeatureDE(casType, "isTerm", "uima.cas.Boolean", featOkTst);
    casFeatCode_isTerm  = (null == casFeat_isTerm) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isTerm).getCode();

  }
}



    