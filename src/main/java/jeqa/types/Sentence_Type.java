
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

/** Sentence
 * Updated by JCasGen Wed Jan 25 19:24:52 JST 2017
 * @generated */
public class Sentence_Type extends TextAnnotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sentence_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sentence_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sentence(addr, Sentence_Type.this);
  			   Sentence_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sentence(addr, Sentence_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Sentence.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("jeqa.types.Sentence");
 
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
      jcas.throwFeatMissing("passageSet", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_passageSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPassageSet(int addr, int v) {
        if (featOkTst && casFeat_passageSet == null)
      jcas.throwFeatMissing("passageSet", "jeqa.types.Sentence");
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
      jcas.throwFeatMissing("documentSet", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_documentSet);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDocumentSet(int addr, int v) {
        if (featOkTst && casFeat_documentSet == null)
      jcas.throwFeatMissing("documentSet", "jeqa.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_documentSet, v);}
    
  
 
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
      jcas.throwFeatMissing("originalText", "jeqa.types.Sentence");
    return ll_cas.ll_getStringValue(addr, casFeatCode_originalText);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOriginalText(int addr, String v) {
        if (featOkTst && casFeat_originalText == null)
      jcas.throwFeatMissing("originalText", "jeqa.types.Sentence");
    ll_cas.ll_setStringValue(addr, casFeatCode_originalText, v);}
    
  
 
  /** @generated */
  final Feature casFeat_morphemeAnalysisList;
  /** @generated */
  final int     casFeatCode_morphemeAnalysisList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getMorphemeAnalysisList(int addr) {
        if (featOkTst && casFeat_morphemeAnalysisList == null)
      jcas.throwFeatMissing("morphemeAnalysisList", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_morphemeAnalysisList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMorphemeAnalysisList(int addr, int v) {
        if (featOkTst && casFeat_morphemeAnalysisList == null)
      jcas.throwFeatMissing("morphemeAnalysisList", "jeqa.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_morphemeAnalysisList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cabochaAnalysisList;
  /** @generated */
  final int     casFeatCode_cabochaAnalysisList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCabochaAnalysisList(int addr) {
        if (featOkTst && casFeat_cabochaAnalysisList == null)
      jcas.throwFeatMissing("cabochaAnalysisList", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_cabochaAnalysisList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCabochaAnalysisList(int addr, int v) {
        if (featOkTst && casFeat_cabochaAnalysisList == null)
      jcas.throwFeatMissing("cabochaAnalysisList", "jeqa.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_cabochaAnalysisList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ipadicChapas;
  /** @generated */
  final int     casFeatCode_ipadicChapas;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getIpadicChapas(int addr) {
        if (featOkTst && casFeat_ipadicChapas == null)
      jcas.throwFeatMissing("ipadicChapas", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_ipadicChapas);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIpadicChapas(int addr, int v) {
        if (featOkTst && casFeat_ipadicChapas == null)
      jcas.throwFeatMissing("ipadicChapas", "jeqa.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_ipadicChapas, v);}
    
  
 
  /** @generated */
  final Feature casFeat_knp;
  /** @generated */
  final int     casFeatCode_knp;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getKnp(int addr) {
        if (featOkTst && casFeat_knp == null)
      jcas.throwFeatMissing("knp", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_knp);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setKnp(int addr, int v) {
        if (featOkTst && casFeat_knp == null)
      jcas.throwFeatMissing("knp", "jeqa.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_knp, v);}
    
  
 
  /** @generated */
  final Feature casFeat_pasList;
  /** @generated */
  final int     casFeatCode_pasList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPasList(int addr) {
        if (featOkTst && casFeat_pasList == null)
      jcas.throwFeatMissing("pasList", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_pasList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPasList(int addr, int v) {
        if (featOkTst && casFeat_pasList == null)
      jcas.throwFeatMissing("pasList", "jeqa.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_pasList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_candidateSentenceList;
  /** @generated */
  final int     casFeatCode_candidateSentenceList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCandidateSentenceList(int addr) {
        if (featOkTst && casFeat_candidateSentenceList == null)
      jcas.throwFeatMissing("candidateSentenceList", "jeqa.types.Sentence");
    return ll_cas.ll_getRefValue(addr, casFeatCode_candidateSentenceList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCandidateSentenceList(int addr, int v) {
        if (featOkTst && casFeat_candidateSentenceList == null)
      jcas.throwFeatMissing("candidateSentenceList", "jeqa.types.Sentence");
    ll_cas.ll_setRefValue(addr, casFeatCode_candidateSentenceList, v);}
    
  
 
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
      jcas.throwFeatMissing("containsQuery", "jeqa.types.Sentence");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_containsQuery);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainsQuery(int addr, boolean v) {
        if (featOkTst && casFeat_containsQuery == null)
      jcas.throwFeatMissing("containsQuery", "jeqa.types.Sentence");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_containsQuery, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Sentence_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_passageSet = jcas.getRequiredFeatureDE(casType, "passageSet", "uima.cas.FSList", featOkTst);
    casFeatCode_passageSet  = (null == casFeat_passageSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_passageSet).getCode();

 
    casFeat_documentSet = jcas.getRequiredFeatureDE(casType, "documentSet", "uima.cas.FSList", featOkTst);
    casFeatCode_documentSet  = (null == casFeat_documentSet) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_documentSet).getCode();

 
    casFeat_originalText = jcas.getRequiredFeatureDE(casType, "originalText", "uima.cas.String", featOkTst);
    casFeatCode_originalText  = (null == casFeat_originalText) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_originalText).getCode();

 
    casFeat_morphemeAnalysisList = jcas.getRequiredFeatureDE(casType, "morphemeAnalysisList", "uima.cas.FSList", featOkTst);
    casFeatCode_morphemeAnalysisList  = (null == casFeat_morphemeAnalysisList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_morphemeAnalysisList).getCode();

 
    casFeat_cabochaAnalysisList = jcas.getRequiredFeatureDE(casType, "cabochaAnalysisList", "uima.cas.FSList", featOkTst);
    casFeatCode_cabochaAnalysisList  = (null == casFeat_cabochaAnalysisList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cabochaAnalysisList).getCode();

 
    casFeat_ipadicChapas = jcas.getRequiredFeatureDE(casType, "ipadicChapas", "jeqa.types.ja.ChaPAS", featOkTst);
    casFeatCode_ipadicChapas  = (null == casFeat_ipadicChapas) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ipadicChapas).getCode();

 
    casFeat_knp = jcas.getRequiredFeatureDE(casType, "knp", "jeqa.types.ja.KNP", featOkTst);
    casFeatCode_knp  = (null == casFeat_knp) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_knp).getCode();

 
    casFeat_pasList = jcas.getRequiredFeatureDE(casType, "pasList", "uima.cas.FSList", featOkTst);
    casFeatCode_pasList  = (null == casFeat_pasList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_pasList).getCode();

 
    casFeat_candidateSentenceList = jcas.getRequiredFeatureDE(casType, "candidateSentenceList", "uima.cas.FSList", featOkTst);
    casFeatCode_candidateSentenceList  = (null == casFeat_candidateSentenceList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_candidateSentenceList).getCode();

 
    casFeat_containsQuery = jcas.getRequiredFeatureDE(casType, "containsQuery", "uima.cas.Boolean", featOkTst);
    casFeatCode_containsQuery  = (null == casFeat_containsQuery) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containsQuery).getCode();

  }
}



    