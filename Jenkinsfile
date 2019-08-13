properties([
  pipelineTriggers([
    [$class: 'GitHubPushTrigger']
  ])
])
node ('master'){
    stage ('checkout'){
        checkout scm
    }
    currentBuild.result = 'FAILURE'
    step([
    $class: 'Mailer', 
    notifyEveryUnstableBuild: true, 
    recipients: emailextrecipients([
      [$class: 'CulpritsRecipientProvider'], 
      [$class: 'RequesterRecipientProvider'],
      [$class: 'FailingTestSuspectsRecipientProvider'],
      [$class: 'UpstreamComitterRecipientProvider']
    ])
  ])
}
node {
      echo "This job is number: ${env.BUILD_ID}"
      echo "The URL is: ${env.JENKINS_URL}"
      stage ('Clean'){
      echo 'Clean Stage'
      sh 'cd build && clean.sh'
  }
    stage ('Setup'){
    echo 'Setup Stage'
    sh 'cd build && setup.sh'
  }
    stage ('Build'){
    echo 'Build Stage'
    sh 'cd build && build_x64.sh'
    sh 'cd build && build_x86.sh'
   }
    stage ('Test'){
    echo 'Test Stage'
    sh 'cd build && test.sh'
  }
}

