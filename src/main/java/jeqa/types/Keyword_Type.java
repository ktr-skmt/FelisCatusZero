
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
import org.apache.uima.jcas.cas.TOP_Type;

/** Keyword
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Keyword_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Keyword_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Keyword_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Keyword(addr, Keyword_Type.this);
  			   Keyword_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Keyword(addr, Keyword_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Keyword.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Keyword");
 
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
      jcas.throwFeatMissing("text", "jeqa.types.Keyword");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "jeqa.types.Keyword");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_expansionSet;
  /** @generated */
  final int     casFeatCode_expansionSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getExpansionSet(int addr) {
        if (featOkTst && casFeat_expansionSet == null)
      jcas.throwFeatMissing("expansionSet", "jeqa.types.Keyword");
    return ll_cas.ll_getRefValue(addr, casFeatCode_expansionSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setExpansionSet(int addr, int v) {
        if (featOkTst && casFeat_expansionSet == null)
      jcas.throwFeatMissing("expansionSet", "jeqa.types.Keyword");
    ll_cas.ll_setRefValue(addr, casFeatCode_expansionSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentenceSet;
  /** @generated */
  final int     casFeatCode_sentenceSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentenceSet(int addr) {
        if (featOkTst && casFeat_sentenceSet == null)
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Keyword");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceSet(int addr, int v) {
        if (featOkTst && casFeat_sentenceSet == null)
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Keyword");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentenceSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_passageSet;
  /** @generated */
  final int     casFeatCode_passageSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPassageSet(int addr) {
        if (featOkTst && casFeat_passageSet == null)
      jcas.throwFeatMissing("passageSet", "jeqa.types.Keyword");
    return ll_cas.ll_getRefValue(addr, casFeatCode_passageSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPassageSet(int addr, int v) {
        if (featOkTst && casFeat_passageSet == null)
      jcas.throwFeatMissing("passageSet", "jeqa.types.Keyword");
    ll_cas.ll_setRefValue(addr, casFeatCode_passageSet, v);}
    
  
 
  /** @generated */
  final Feature casFeat_documentSet;
  /** @generated */
  final int     casFeatCode_documentSet;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getDocumentSet(int addr) {
        if (featOkTst && casFeat_documentSet == null)
      jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    return ll_cas.ll_getRefValue(addr, casFeatCode_documentSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocumentSet(int addr, int v) {
        if (featOkTst && casFeat_documentSet == null)
      jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    ll_cas.ll_setRefValue(addr, casFeatCode_documentSet, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getDocumentSet(int addr, int i) {
        if (featOkTst && casFeat_documentSet == null)
      jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_documentSet), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_documentSet), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_documentSet), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setDocumentSet(int addr, int i, int v) {
        if (featOkTst && casFeat_documentSet == null)
      jcas.throwFeatMissing("documentSet", "jeqa.types.Keyword");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_documentSet), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_documentSet), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_documentSet), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_isMandatory;
  /** @generated */
  final int     casFeatCode_isMandatory;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getIsMandatory(int addr) {
        if (featOkTst && casFeat_isMandatory == null)
      jcas.throwFeatMissing("isMandatory", "jeqa.types.Keyword");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_isMandatory);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIsMandatory(int addr, boolean v) {
        if (featOkTst && casFeat_isMandatory == null)
      jcas.throwFeatMissing("isMandatory", "jeqa.types.Keyword");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_isMandatory, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Keyword_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_expansionSet = jcas.getRequiredFeatureDE(casType, "expansionSet", "uima.cas.FSList", featOkTst);
    casFeatCode_expansionSet  = (null == casFeat_expansionSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_expansionSet).getCode();

 
    casFeat_sentenceSet = jcas.getRequiredFeatureDE(casType, "sentenceSet", "uima.cas.FSList", featOkTst);
    casFeatCode_sentenceSet  = (null == casFeat_sentenceSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceSet).getCode();

 
    casFeat_passageSet = jcas.getRequiredFeatureDE(casType, "passageSet", "uima.cas.FSList", featOkTst);
    casFeatCode_passageSet  = (null == casFeat_passageSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_passageSet).getCode();

 
    casFeat_documentSet = jcas.getRequiredFeatureDE(casType, "documentSet", "uima.cas.FSArray", featOkTst);
    casFeatCode_documentSet  = (null == casFeat_documentSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentSet).getCode();

 
    casFeat_isMandatory = jcas.getRequiredFeatureDE(casType, "isMandatory", "uima.cas.Boolean", featOkTst);
    casFeatCode_isMandatory  = (null == casFeat_isMandatory) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_isMandatory).getCode();

  }
}



    