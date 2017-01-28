
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

/** IRで取得したDocument
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Document_Type extends TextAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Document_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Document_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Document(addr, Document_Type.this);
  			   Document_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Document(addr, Document_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Document.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Document");
 
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
      jcas.throwFeatMissing("passageSet", "jeqa.types.Document");
    return ll_cas.ll_getRefValue(addr, casFeatCode_passageSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPassageSet(int addr, int v) {
        if (featOkTst && casFeat_passageSet == null)
      jcas.throwFeatMissing("passageSet", "jeqa.types.Document");
    ll_cas.ll_setRefValue(addr, casFeatCode_passageSet, v);}
    
  
 
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
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Document");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceSet(int addr, int v) {
        if (featOkTst && casFeat_sentenceSet == null)
      jcas.throwFeatMissing("sentenceSet", "jeqa.types.Document");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentenceSet, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Document_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_passageSet = jcas.getRequiredFeatureDE(casType, "passageSet", "uima.cas.FSList", featOkTst);
    casFeatCode_passageSet  = (null == casFeat_passageSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_passageSet).getCode();

 
    casFeat_sentenceSet = jcas.getRequiredFeatureDE(casType, "sentenceSet", "uima.cas.FSList", featOkTst);
    casFeatCode_sentenceSet  = (null == casFeat_sentenceSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceSet).getCode();

  }
}



    