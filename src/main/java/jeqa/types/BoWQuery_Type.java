
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

/** Bag of Words type Query
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class BoWQuery_Type extends Query_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (BoWQuery_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = BoWQuery_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new BoWQuery(addr, BoWQuery_Type.this);
  			   BoWQuery_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new BoWQuery(addr, BoWQuery_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = BoWQuery.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.BoWQuery");
 
  /** @generated */
  final Feature casFeat_indriQuery;
  /** @generated */
  final int     casFeatCode_indriQuery;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getIndriQuery(int addr) {
        if (featOkTst && casFeat_indriQuery == null)
      jcas.throwFeatMissing("indriQuery", "jeqa.types.BoWQuery");
    return ll_cas.ll_getRefValue(addr, casFeatCode_indriQuery);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIndriQuery(int addr, int v) {
        if (featOkTst && casFeat_indriQuery == null)
      jcas.throwFeatMissing("indriQuery", "jeqa.types.BoWQuery");
    ll_cas.ll_setRefValue(addr, casFeatCode_indriQuery, v);}
    
  
 
  /** @generated */
  final Feature casFeat_algorithm;
  /** @generated */
  final int     casFeatCode_algorithm;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAlgorithm(int addr) {
        if (featOkTst && casFeat_algorithm == null)
      jcas.throwFeatMissing("algorithm", "jeqa.types.BoWQuery");
    return ll_cas.ll_getStringValue(addr, casFeatCode_algorithm);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAlgorithm(int addr, String v) {
        if (featOkTst && casFeat_algorithm == null)
      jcas.throwFeatMissing("algorithm", "jeqa.types.BoWQuery");
    ll_cas.ll_setStringValue(addr, casFeatCode_algorithm, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public BoWQuery_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_indriQuery = jcas.getRequiredFeatureDE(casType, "indriQuery", "jeqa.types.Keyword", featOkTst);
    casFeatCode_indriQuery  = (null == casFeat_indriQuery) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_indriQuery).getCode();

 
    casFeat_algorithm = jcas.getRequiredFeatureDE(casType, "algorithm", "uima.cas.String", featOkTst);
    casFeatCode_algorithm  = (null == casFeat_algorithm) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_algorithm).getCode();

  }
}



    