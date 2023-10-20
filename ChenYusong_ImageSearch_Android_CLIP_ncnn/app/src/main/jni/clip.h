#ifndef GPT2_H
#define GPT2_H

#include <map>
#include <net.h>
#include <vector>
#include "opencv2/opencv.hpp"

class CLIP
{
public:
    CLIP();

    int load(AAssetManager* mgr, std::string vocab);
    void encode_image(ncnn::Mat in, cv::Mat& feat);
    void encode_text(std::string text, cv::Mat& feat);
    void encode_text(std::vector<int> text, cv::Mat& feat);

private:
    std::vector<std::string> stringSplit(const std::string& str, char delim);
    std::vector<int> tokenize(std::string token);

private:
    ncnn::Net net;
    ncnn::UnlockedPoolAllocator blob_pool_allocator;
    ncnn::PoolAllocator workspace_pool_allocator;

    std::map<std::string, int> tokenizer_token2idx;

    const float mean_vals[3] = { 0.48145466f * 255.f, 0.4578275f * 255.f, 0.40821073f * 255.f };
    const float norm_vals[3] = { 1 / 0.26862954f / 255.f, 1 / 0.26130258f / 255.f, 1 / 0.27577711f / 255.f };
};

#endif // NANODET_H
