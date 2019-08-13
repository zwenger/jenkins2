pipeline{
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
        bat 'cd build && clean.cmd'
    }
      stage ('Setup'){
      echo 'Setup Stage'
      bat 'cd build && setup.cmd'
    }
      stage ('Build'){
      echo 'Build Stage'
      bat 'cd build && build_x64.cmd'
      bat 'cd build && build_x86.cmd'
    }
      stage ('Test'){
      echo 'Test Stage'
      bat 'cd build && test.cmd'
    }
  }
}
