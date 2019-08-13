#!groovy

stage ('build'){
        sh './build/build_x64.sh'
        sh './build/build_x86.sh'
}