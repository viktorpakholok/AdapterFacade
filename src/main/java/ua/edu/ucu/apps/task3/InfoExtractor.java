package ua.edu.ucu.apps.task3;

public interface InfoExtractor {
    Company extractInfo(String website);
    String getExtractionMethodName();
}
