public interface OnDetectionListener {
    void onSucceeded(String actualContent);
    void onFailed(String errMsg);
}
