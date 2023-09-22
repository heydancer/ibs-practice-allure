node('master') {
    stage('clean') {
        cleanWs()
    }
    
    stage('clone') {
        git branch: BRANCH, url: 'https://github.com/heydancer/ibs-practice-allure.git'
    }
    
    stage('build') {
        sh '''
            mvn clean install
        '''
    }
}
