LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := Sample
LOCAL_SRC_FILES := Sample.c

include $(BUILD_SHARED_LIBRARY)
