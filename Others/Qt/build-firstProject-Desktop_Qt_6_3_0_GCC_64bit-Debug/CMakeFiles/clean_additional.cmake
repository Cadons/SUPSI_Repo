# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "CMakeFiles/firstProject_autogen.dir/AutogenUsed.txt"
  "CMakeFiles/firstProject_autogen.dir/ParseCache.txt"
  "firstProject_autogen"
  )
endif()
