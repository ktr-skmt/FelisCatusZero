
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

/** Passage
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Passage_Type extends TextAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Passage_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Passage_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Passage(addr, Passage_Type.this);
  			   Passage_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Passage(addr, Passage_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Passage.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Passage");
 
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
      jcas.throwFeatMissing("documentSet", "jeqa.types.Passage");
    return ll_cas.ll_getRefValue(addr, casFeatCode_documentSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocumentSet(int addr, int v) {
        if (featOkTst && casFeat_documentSet == null)
      jcas.throwFeatMissing("documentSet", "jeqa.types.Passage");
    ll_cas.ll_setRefValue(addr, casFeatCode_documentSet, v);}
    
  
 
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
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceSet(int addr, int v) {
        if (featOkTst && casFeat_sentenceSet == null)
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentenceSet, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getSentenceSet(int addr, int i) {
        if (featOkTst && casFeat_sentenceSet == null)
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setSentenceSet(int addr, int i, int v) {
        if (featOkTst && casFeat_sentenceSet == null)
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Passage");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_containsQuery;
  /** @generated */
  final int     casFeatCode_containsQuery;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getContainsQuery(int addr) {
        if (featOkTst && casFeat_containsQuery == null)
      jcas.throwFeatMissing("containsQuery", "jeqa.types.Passage");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_containsQuery);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainsQuery(int addr, boolean v) {
        if (featOkTst && casFeat_containsQuery == null)
      jcas.throwFeatMissing("containsQuery", "jeqa.types.Passage");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_containsQuery, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Passage_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_documentSet = jcas.getRequiredFeatureDE(casType, "documentSet", "uima.cas.FSList", featOkTst);
    casFeatCode_documentSet  = (null == casFeat_documentSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentSet).getCode();

 
    casFeat_sentenceSet = jcas.getRequiredFeatureDE(casType, "sentenceSet", "uima.cas.FSArray", featOkTst);
    casFeatCode_sentenceSet  = (null == casFeat_sentenceSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceSet).getCode();

 
    casFeat_containsQuery = jcas.getRequiredFeatureDE(casType, "containsQuery", "uima.cas.Boolean", featOkTst);
    casFeatCode_containsQuery  = (null == casFeat_containsQuery) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containsQuery).getCode();

  }
}



    