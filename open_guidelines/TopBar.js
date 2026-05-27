import React from 'react';
import { StyleSheet, View, Text } from 'react-native';

const TopBar = ({ onNavigate }) => {
    return (
        <View style={styles.bar}>
            <Text onPress={() => onNavigate('title.html')}>← Back</Text>    
        </View>
    )
}

const styles = StyleSheet.create({
    bar: {
        height: "10%",
        width: "100%",
        paddingLeft: "5%",
        paddingRight: "5%",
        alignItems: "center",
        justifyContent: "center"
    }
})

export default TopBar;