
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

/** word embedding
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class WordEmbedding_Type extends TOP_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (WordEmbedding_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = WordEmbedding_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new WordEmbedding(addr, WordEmbedding_Type.this);
  			   WordEmbedding_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new WordEmbedding(addr, WordEmbedding_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = WordEmbedding.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.WordEmbedding");
 
  /** @generated */
  final Feature casFeat_embeddingMethod;
  /** @generated */
  final int     casFeatCode_embeddingMethod;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getEmbeddingMethod(int addr) {
        if (featOkTst && casFeat_embeddingMethod == null)
      jcas.throwFeatMissing("embeddingMethod", "jeqa.types.WordEmbedding");
    return ll_cas.ll_getStringValue(addr, casFeatCode_embeddingMethod);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEmbeddingMethod(int addr, String v) {
        if (featOkTst && casFeat_embeddingMethod == null)
      jcas.throwFeatMissing("embeddingMethod", "jeqa.types.WordEmbedding");
    ll_cas.ll_setStringValue(addr, casFeatCode_embeddingMethod, v);}
    
  
 
  /** @generated */
  final Feature casFeat_vector;
  /** @generated */
  final int     casFeatCode_vector;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getVector(int addr) {
        if (featOkTst && casFeat_vector == null)
      jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    return ll_cas.ll_getRefValue(addr, casFeatCode_vector);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVector(int addr, int v) {
        if (featOkTst && casFeat_vector == null)
      jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    ll_cas.ll_setRefValue(addr, casFeatCode_vector, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public float getVector(int addr, int i) {
        if (featOkTst && casFeat_vector == null)
      jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getFloatArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_vector), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_vector), i);
	return ll_cas.ll_getFloatArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_vector), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setVector(int addr, int i, float v) {
        if (featOkTst && casFeat_vector == null)
      jcas.throwFeatMissing("vector", "jeqa.types.WordEmbedding");
    if (lowLevelTypeChecks)
      ll_cas.ll_setFloatArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_vector), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_vector), i);
    ll_cas.ll_setFloatArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_vector), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public WordEmbedding_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_embeddingMethod = jcas.getRequiredFeatureDE(casType, "embeddingMethod", "uima.cas.String", featOkTst);
    casFeatCode_embeddingMethod  = (null == casFeat_embeddingMethod) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_embeddingMethod).getCode();

 
    casFeat_vector = jcas.getRequiredFeatureDE(casType, "vector", "uima.cas.FloatArray", featOkTst);
    casFeatCode_vector  = (null == casFeat_vector) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_vector).getCode();

  }
}



    