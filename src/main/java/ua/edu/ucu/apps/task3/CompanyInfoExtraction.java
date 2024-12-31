package ua.edu.ucu.apps.task3;

import java.util.List;

public class CompanyInfoExtraction {
    private PDLReader pdlReader;
    private ScraperAndAi scraperAndAi;
    private Brandfetch brandfetch;

    public CompanyInfoExtraction() {
        pdlReader = new PDLReader();
        scraperAndAi = new ScraperAndAi();
        brandfetch = new Brandfetch();
    }

    public Company extractInfo(String companyToFind) {

        List<InfoExtractor> infoExtractors 
        = List.of(pdlReader, scraperAndAi, brandfetch);

        Company resCompany = new Company();

        for (InfoExtractor infoExtractor : infoExtractors) {

            Company company = infoExtractor.extractInfo(companyToFind);
            String fromInfo 
            = " (from " + infoExtractor.getExtractionMethodName() + ")";

            if (resCompany.getName().equals("NaN") 
            && !company.getName().equals("NaN")) {
                resCompany.setName(company.getName() + fromInfo);
            }

            if (resCompany.getDescription().equals("NaN") 
            && !company.getDescription().equals("NaN")) {
                resCompany.setDescription(company.getDescription() + fromInfo);
            }

            if (resCompany.getLogo().equals("NaN") 
            && !company.getLogo().equals("NaN")) {
                resCompany.setLogo(company.getLogo() + fromInfo);
            }

            if (!resCompany.getName().equals("NaN") 
            && !resCompany.getDescription().equals("NaN") 
            && !resCompany.getLogo().equals("NaN")) {
                break;
            }
        }

        return resCompany;
    }
}
