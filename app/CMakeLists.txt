cmake_minimum_required(VERSION 3.4.1)

set(libs "${CMAKE_SOURCE_DIR}/libs")

add_library(native-lib SHARED src/cpp/native-lib.cpp)

add_library(qt-lib SHARED IMPORTED)
add_library(qt5-lib SHARED IMPORTED)
add_library(cpp-lib SHARED IMPORTED)

set_target_properties(qt-lib PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/libqt-android.so)
set_target_properties(qt5-lib PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/libQt5Core.so)
set_target_properties(cpp-lib PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/libc++_shared.so)

include_directories(src/cpp/)

find_library(log-lib log)

target_link_libraries(native-lib cpp-lib qt5-lib qt-lib ${log-lib})
